package ir.alirezaalijani.spring.retry.demo.webservice.endpoint;

import ir.alirezaalijani.spring.retry.demo.converter.UserConverter;
import ir.alirezaalijani.spring.retry.demo.model.dao.DbUser;
import ir.alirezaalijani.spring.retry.demo.model.User;
import ir.alirezaalijani.spring.retry.demo.webrepository.repositories.repository.DaoUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("retry")
public class CustomRetryController {
    private static final Logger log= LoggerFactory.getLogger(CustomRetryController.class);
    private final DaoUserRepository userRepository;

    public CustomRetryController(DaoUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        log.info("new request for user by id: {}",id);
        Optional<DbUser> findUser = userRepository.findById(id);
        if (findUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        User user= UserConverter.getUser(findUser.get());
        return ResponseEntity.ok(user);
    }

    @PostMapping("user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        DbUser dbUser =userRepository.save(UserConverter.getDbUser(user));
        return ResponseEntity.ok(UserConverter.getUser(dbUser));
    }
}
