package com.example.Library.Control.Sort.UserSort;

import com.example.Library.Model.User;
import org.springframework.stereotype.Controller;

import java.util.Comparator;

@Controller
public class UserSortByMembesrshipType implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getMembershipType().compareTo(o2.getMembershipType());
    }
}
