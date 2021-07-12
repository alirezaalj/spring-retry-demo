package ir.alirezaalijani.spring.retry.demo.webrepository.repositories.repository;

import ir.alirezaalijani.spring.retry.demo.model.dao.DbUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoUserRepository extends CrudRepository<DbUser,Integer> {
}
