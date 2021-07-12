package ir.alirezaalijani.spring.retry.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "client-service")
public class ClientServiceConfigData {
    private String helloMessage;
    private String webServiceUrl;
}
