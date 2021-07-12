package ir.alirezaalijani.spring.retry.demo.converter;

import ir.alirezaalijani.spring.retry.demo.model.User;
import ir.alirezaalijani.spring.retry.demo.model.dao.DbUser;

public class UserConverter {
    public static DbUser getDbUser(User user){
        return new DbUser(user.getId(),user.getUsername(),user.getMessage());
    }
    public static User getUser(DbUser user){
        return new User(user.getId(),user.getUsername(),user.getMessage());
    }
}
