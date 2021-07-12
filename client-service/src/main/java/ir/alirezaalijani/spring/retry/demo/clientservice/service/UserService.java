package ir.alirezaalijani.spring.retry.demo.clientservice.service;

import ir.alirezaalijani.spring.retry.demo.ClientServiceConfigData;
import ir.alirezaalijani.spring.retry.demo.RetryConfigData;
import ir.alirezaalijani.spring.retry.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final static Logger log= LoggerFactory.getLogger(UserService.class);
    private final WebClient webClient;
    private final RestTemplate restTemplate;
    private final ClientServiceConfigData clientServiceConfigData;
    private final RetryTemplate retryTemplate;
    private final RetryConfigData retryConfigData;
    private final String userServiceUrl;
    public UserService(WebClient webClient, RestTemplate restTemplate, ClientServiceConfigData clientServiceConfigData, RetryTemplate retryTemplate, RetryConfigData retryConfigData) {
        this.webClient = webClient;
        this.restTemplate = restTemplate;
        this.clientServiceConfigData = clientServiceConfigData;
        this.retryTemplate = retryTemplate;
        this.retryConfigData = retryConfigData;
        this.userServiceUrl=clientServiceConfigData.getWebServiceUrl()+"/retry/user/";
    }

    public User findUserById(int id){
        // {serverUrl}/retry/user/{id}
        String findByIdPath=this.userServiceUrl+id;
//        return retryTemplate.execute(context->{
//            log.info("---- retry get user by id: {}",id);
//            return restTemplate.getForObject(findByIdPath,User.class);
//        });
        return retryTemplate.execute(retryContext -> {
            log.info("---- retry get user by id: {}",id);
            return webClient.get()
                    .uri(findByIdPath)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .retrieve()
                    .bodyToMono(User.class).block();
        });
    }
    public User addNewUser(User user){
        // {serverUrl}/retry/user/{id}
        String addByIdPath=this.userServiceUrl;
        return retryTemplate.execute(retryContext -> {
            log.info("---- retry post user : {}",user);
            return webClient.post()
                    .uri(addByIdPath)
                    .body(Mono.just(user), User.class)
                    .retrieve()
                    .bodyToMono(User.class).block();
        });
    }
}
