package com.example.Library.Control.BookController;


import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Controller
public class BookRecommendation {

    private HashMap<String, Set<String>> recommendation;

    public BookRecommendation(){
        recommendation=new HashMap<>();
    }

    public void addRecommendation(String genre, String bookTitle) {
        recommendation.computeIfAbsent(genre, k -> new HashSet<>()).add(bookTitle);
    }

    public Set<String> getRecommendations(String genre) {
        return recommendation.getOrDefault(genre, Collections.emptySet());
    }

    public void removeRecommendation(String genre, String bookTitle) {
        Set<String> books = recommendation.get(genre);
        if (books != null) {
            books.remove(bookTitle);
            if (books.isEmpty()) {
                recommendation.remove(genre);
            }
        }
    }

}
