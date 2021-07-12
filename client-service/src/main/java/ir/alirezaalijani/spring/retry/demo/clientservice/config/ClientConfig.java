package ir.alirezaalijani.spring.retry.demo.clientservice.config;

import ir.alirezaalijani.spring.retry.demo.RetryConfigData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClientConfig {

    private RetryConfigData retryConfigData;

    public ClientConfig(RetryConfigData configData) {
        this.retryConfigData = configData;
    }

    @Bean
    WebClient getWebClient(){
        return WebClient.builder().build();
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
        retryableExceptions.put(RestClientException.class, true); // HTTP 404, 503, etc.
        retryableExceptions.put(WebClientException.class, true); // HTTP 404, 503, etc
        // Retry exception blacklist
        retryableExceptions.put(HttpClientErrorException.BadRequest.class, false); // HTTP 400
        retryableExceptions.put(HttpServerErrorException.InternalServerError.class, false); // HTTP 500

        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(retryConfigData.getInitialIntervalMs());
        exponentialBackOffPolicy.setMaxInterval(retryConfigData.getMaxIntervalMs());
        exponentialBackOffPolicy.setMultiplier(retryConfigData.getMultiplier());

        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(retryConfigData.getMaxAttempts(),retryableExceptions);

        retryTemplate.setRetryPolicy(simpleRetryPolicy);

        return retryTemplate;
    }
}
