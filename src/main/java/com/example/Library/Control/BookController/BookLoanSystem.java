package com.example.Library.Control.BookController;

import com.example.Library.Model.Book;
import com.example.Library.Model.User;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class BookLoanSystem {

    private HashMap<String, Set<User>> loans;
    private Map<String, Book> books;

    public BookLoanSystem() {
        this.loans = new HashMap<>();
        this.books=new HashMap<>();
    }

    public void borrowBook(String ISBN, User user) {
        loans.putIfAbsent(ISBN, new HashSet<>());
        loans.get(ISBN).add(user);
    }

    public void returnBook(String ISBN, User user) {
        if (loans.containsKey(ISBN)) {
            loans.get(ISBN).remove(user);
            if (loans.get(ISBN).isEmpty()) {
                loans.remove(ISBN);
            }
        }
    }

    public Set<User> getCurrentBorrowers(String ISBN) {
        return loans.getOrDefault(ISBN, new HashSet<>());
    }

    public Map<String, Set<User>> getAllLoanBooks() {
        return loans;
    }

    public Book getLoanBook(String ISBN) {
        return books.get(ISBN);
    }

    public void addBook(Book book) {
        books.put(book.getISBN(), book);
    }

    public void removeBook(String ISBN) {
        books.remove(ISBN);
    }


}
