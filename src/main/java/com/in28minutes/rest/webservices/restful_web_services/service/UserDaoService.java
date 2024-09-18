package com.in28minutes.rest.webservices.restful_web_services.service;

import com.in28minutes.rest.webservices.restful_web_services.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class UserDaoService {

    private static int id = 0;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(++id, "Justin Rentie", LocalDate.of(1996, 5, 9)));
        users.add(new User(++id, "Jon Doe", LocalDate.of(1980, 1, 20)));
        users.add(new User(++id, "Mary Jane", LocalDate.of(1995, 4, 20)));
    }

    public List<User> findAll(){
        return users;
    }

    public int addUser(User user){
        user.setId(++id);
        users.add(user);
        return user.getId();
    }

    public User getUserById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public Boolean deleteUserById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        try{
            users.removeIf(predicate);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
