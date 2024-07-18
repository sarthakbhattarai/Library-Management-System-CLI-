package com.example.Library.Control.Sort.UserSort;

import com.example.Library.Model.User;
import org.springframework.stereotype.Controller;

import java.util.Comparator;

@Controller
public class UserSortByName implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
