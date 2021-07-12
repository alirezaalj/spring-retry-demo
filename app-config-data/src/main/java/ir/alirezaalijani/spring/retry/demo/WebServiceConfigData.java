package ir.alirezaalijani.spring.retry.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "web-service")
public class WebServiceConfigData {
    private String helloMessage;
}
