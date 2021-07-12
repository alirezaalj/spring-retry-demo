package ir.alirezaalijani.spring.retry.demo.webrepository.repositories.repository;

import ir.alirezaalijani.spring.retry.demo.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<User,Integer> {
}
