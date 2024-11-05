package com.rest.webservices.restful_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class userDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount=0;
    static {
        users.add(new User(++userCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount,"Riddhima", LocalDate.now().minusYears(25)));
        users.add(new User(++userCount,"Anchal", LocalDate.now().minusYears(20)));
        users.add(new User(++userCount,"Ashutosh", LocalDate.now().minusYears(24)));
    }
    public List<User> findAll(){
        return users;
    }
    public User findOne(Integer id){
        Predicate<? super User> predicate = User -> User.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }
    public void deleteById(Integer id){
        Predicate<? super User> predicate = User -> User.getId().equals(id);
        users.removeIf(predicate);
    }
    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }
}
