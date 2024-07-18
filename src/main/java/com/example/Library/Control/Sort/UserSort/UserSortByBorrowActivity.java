package com.example.Library.Control.Sort.UserSort;

import com.example.Library.Model.User;

import java.util.Comparator;
import java.util.Map;

public class UserSortByBorrowActivity implements Comparator<Map.Entry<User, Integer>> {
    @Override
    public int compare(Map.Entry<User, Integer> e1, Map.Entry<User, Integer> e2) {
        return e2.getValue().compareTo(e1.getValue());
    }
}
