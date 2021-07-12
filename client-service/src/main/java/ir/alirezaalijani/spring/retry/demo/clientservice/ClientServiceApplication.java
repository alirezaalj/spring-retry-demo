package ir.alirezaalijani.spring.retry.demo.clientservice;

import ir.alirezaalijani.spring.retry.demo.ClientServiceConfigData;
import ir.alirezaalijani.spring.retry.demo.clientservice.service.UserService;
import ir.alirezaalijani.spring.retry.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = "ir.alirezaalijani.spring.retry.demo")
public class ClientServiceApplication implements CommandLineRunner {
    private static final Logger log= LoggerFactory.getLogger(ClientServiceApplication.class);
    private final ClientServiceConfigData clientServiceConfigData;
    private final UserService userService;
    private static final Scanner SCANNER =new Scanner(System.in);

    public ClientServiceApplication(ClientServiceConfigData clientServiceConfigData, UserService userService) {
        this.clientServiceConfigData = clientServiceConfigData;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("hello message: {}",clientServiceConfigData.getHelloMessage());
        runInfinity();
    }

    private void runInfinity(){
        new Thread(() -> {
          while (true){
              System.out.print("Pleas enter user id to find: ");
              int id = SCANNER.nextInt();
              log.info("start to get user from web service");
              User findUser = userService.findUserById(id);
              log.info("find user by id is don: {}",findUser);
          }
        }).start();
    }
}
