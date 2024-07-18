package com.example.Library.Utility;


import com.example.Library.Control.BookController.BookLoanSystem;
import com.example.Library.Control.BookController.GenreBasedCatalog;
import com.example.Library.Enum.BookEnum.Genre;
import com.example.Library.Model.Book;
import com.example.Library.Model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibraryUtility {

    public static TreeMap<Integer, Set<Book>> getMostBorrowedBooks(BookLoanSystem bookLoanSystem) {
        Map<Book, Integer> borrowCounts = new HashMap<>();

        for (String isbn : bookLoanSystem.getAllLoanBooks().keySet()) {
            Book book = bookLoanSystem.getLoanBook(isbn);
            int count = bookLoanSystem.getCurrentBorrowers(isbn).size();
            borrowCounts.put(book, count);
        }

        TreeMap<Integer, Set<Book>> sortedByBorrowCount = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Book, Integer> entry : borrowCounts.entrySet()) {
            sortedByBorrowCount.putIfAbsent(entry.getValue(), new HashSet<>());
            sortedByBorrowCount.get(entry.getValue()).add(entry.getKey());
        }

        return sortedByBorrowCount;
    }

    public static HashSet<User> getUsersWithOverdueBooks(BookLoanSystem bookLoanSystem, Map<String, Long> dueDates, long currentDate) {
        HashSet<User> overdueUsers = new HashSet<>();

        for (Map.Entry<String, Set<User>> entry : bookLoanSystem.getAllLoanBooks().entrySet()) {
            String isbn = entry.getKey();
            Set<User> users = entry.getValue();

            if (dueDates.get(isbn) < currentDate) {
                overdueUsers.addAll(users);
            }
        }
        return overdueUsers;
    }

    public static TreeMap<Integer, Set<String>> getGenrePopularityRanking(GenreBasedCatalog genreBasedCatalog) {
        Map<String, Integer> genrePopularity = new HashMap<>();

        for (Map.Entry<Genre, Set<Book>> entry : genreBasedCatalog.getAllGenres().entrySet()) {
            Genre genre = entry.getKey();
            int count = entry.getValue().size();
            genrePopularity.put(genre.name(), count);
        }

        TreeMap<Integer, Set<String>> sortedByPopularity = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<String, Integer> entry : genrePopularity.entrySet()) {
            sortedByPopularity.putIfAbsent(entry.getValue(), new HashSet<>());
            sortedByPopularity.get(entry.getValue()).add(entry.getKey());
        }

        return sortedByPopularity;
    }


}
