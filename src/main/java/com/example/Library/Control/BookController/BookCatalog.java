package com.example.Library.Control.BookController;

import com.example.Library.Model.Book;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class BookCatalog {



    private HashMap<String, Book> books;

    public BookCatalog() {
        this.books=new HashMap<>();
    }

    public void addBook(Book book){

        books.put(book.getISBN(),book);
    }

    public void removeBook(String ISBN){
        books.remove(ISBN);
    }

    public Book getBook(String ISBN){
        return books.get(ISBN);
    }

    public Collection<Book> getAllBook(){
        return books.values();
    }

    public List<Book> searchByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public Set<Book> searchByYearRange(int startYear, int endYear) {
        return books.values().stream()
                .filter(book -> book.getPublicationYear() >= startYear && book.getPublicationYear() <= endYear)
                .collect(Collectors.toSet());
    }


}
