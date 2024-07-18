package com.example.Library.Repository;

import com.example.Library.Control.BookController.BookCatalog;
import com.example.Library.Control.BookController.BookLoanSystem;
import com.example.Library.Control.BookController.BookRecommendation;
import com.example.Library.Control.BookController.GenreBasedCatalog;
import com.example.Library.Control.Sort.BookSort.BookSortByTitle;
import com.example.Library.Control.UserController.UserActivityTracker;
import com.example.Library.Control.UserController.UserManager;
import com.example.Library.Model.Book;
import com.example.Library.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Data
@AllArgsConstructor
@Repository
public class Library {

    private static Library instance;

    @Autowired
    private BookCatalog bookCat;
    @Autowired
    private UserManager userManage;
    @Autowired
    private BookLoanSystem blSystem;
    @Autowired
    private GenreBasedCatalog genreCat;
    @Autowired
    private UserActivityTracker tracker;
    @Autowired
    private BookRecommendation bookRecommendationSystem;

    private Map<String, Book> bookCache;



    public Library(){
        this.userManage=new UserManager();
        this.bookCat=new BookCatalog();
        this.blSystem=new BookLoanSystem();
        this.genreCat = new GenreBasedCatalog();
        this.tracker = new UserActivityTracker();
        this.bookRecommendationSystem= new BookRecommendation();

        this.bookCache = Collections.synchronizedMap(new LinkedHashMap<String, Book>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Book> eldest) {
                return size() > 10;
            }
        });
    }

    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }
    public synchronized void addBook(Book book){
        bookCat.addBook(book);
        genreCat.addBookGenre(book);
    }

    public synchronized void removeBook(String ISBN){

        Book book = bookCat.getBook(ISBN);
        if(book!=null){
            bookCat.removeBook(ISBN);
            genreCat.removeBook(book);
        }else{
            System.out.println("Could not find the book");
        }

    }

    public synchronized void registerUser(User user){
        userManage.addUser(user);
    }

    public synchronized void borrowBook(String ISBN, User user) {
        Book book = bookCat.getBook(ISBN);
        if (book != null) {
            blSystem.borrowBook(ISBN, user);
            tracker.updateUserActivity(user);
        }
    }

    public synchronized void returnBook(String ISBN, User user){
        blSystem.returnBook(ISBN,user);
    }

    public TreeSet<Book> getSortedBooksByTitle() {
        return new TreeSet<>(new BookSortByTitle());
    }

    public TreeMap<Integer, Set<User>> getSortedUsersByActivity() {
        return tracker.getMostActiveUser();
    }

    public void cacheBook(Book book) {
        bookCache.put(book.getISBN(), book);
    }
    public Book getCachedBook(String ISBN) {
        return bookCache.get(ISBN);
    }
    public void clearCache() {
        bookCache.clear();
    }
}

