package com.example.Library.Control.UserController;


import com.example.Library.Model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class UserManager {

    HashMap<String, User> users;
    public UserManager(){
        this.users = new HashMap<>();
    }

    public void addUser(User user){
        users.put(user.getUserId(),user);
    }

    public void removeUser(String userId){
        users.remove(userId);
    }

    public User getUser(String userId){
        return users.get(userId);
    }

    public Collection<User> getAllUser(){
        return users.values();
    }

}
