package ir.alirezaalijani.spring.retry.demo.webservice;

import ir.alirezaalijani.spring.retry.demo.WebServiceConfigData;
import ir.alirezaalijani.spring.retry.demo.model.dao.DbUser;
import ir.alirezaalijani.spring.retry.demo.webrepository.repositories.repository.DaoUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = "ir.alirezaalijani.spring.retry.demo")
@EntityScan("ir.alirezaalijani.spring.retry.demo.model")
@EnableJpaRepositories("ir.alirezaalijani.spring.retry.demo.webrepository.repositories.repository")
public class WebServiceApplication implements CommandLineRunner {
    private static final Logger log= LoggerFactory.getLogger(WebServiceApplication.class);
    private final WebServiceConfigData webServiceConfigData;
    private final DaoUserRepository userRepository;
    public WebServiceApplication(WebServiceConfigData webServiceConfigData, DaoUserRepository userRepository) {
        this.webServiceConfigData = webServiceConfigData;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("hello message: {}",webServiceConfigData.getHelloMessage());
        log.info("add temp data to hsqldb");
        userRepository.saveAll(Arrays.asList(
                new DbUser(1,"alireza","hi to all"),
                new DbUser(2,"erfan","hi im erfan"),
                new DbUser(3,"mehdi","hi to erfan im mehdi"),
                new DbUser(4,"reza","hi to all im reza")
        )).forEach(user -> log.info("add user {} success",user.getUsername()));
    }
}
