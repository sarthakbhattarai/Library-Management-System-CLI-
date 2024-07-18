package com.example.Library.Control.UserController;


import com.example.Library.Model.User;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class UserActivityTracker {

    private HashMap<User,Integer> userActivity;
    private TreeMap<Integer, Set<User>> activityRanking;

    public UserActivityTracker(){
        userActivity=new HashMap<>();
        activityRanking=new TreeMap<>(Collections.reverseOrder());
    }

    public void updateUserActivity(User user) {
        int oldCount = userActivity.getOrDefault(user, 0);
        userActivity.put(user, oldCount + 1);

        activityRanking.getOrDefault(oldCount, new HashSet<>()).remove(user);
        if (activityRanking.getOrDefault(oldCount, new HashSet<>()).isEmpty()) {
            activityRanking.remove(oldCount);
        }
        activityRanking.putIfAbsent(oldCount + 1, new HashSet<>());
        activityRanking.get(oldCount + 1).add(user);
    }

    public TreeMap<Integer,Set<User>> getMostActiveUser(){
        return activityRanking;
    }

    public TreeMap<Integer, Set<User>> getLeastActiveUser(){
        return (TreeMap<Integer, Set<User>>) activityRanking.descendingMap();
    }



}
