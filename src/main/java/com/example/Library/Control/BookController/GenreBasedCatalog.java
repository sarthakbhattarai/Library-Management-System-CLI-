package com.example.Library.Control.BookController;


import com.example.Library.Enum.BookEnum.Genre;
import com.example.Library.Model.Book;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class GenreBasedCatalog {

    private TreeMap<Genre, Set<Book>> genreCatalog;

    public GenreBasedCatalog(){
        genreCatalog=new TreeMap<>();
    }

    public void addBookGenre(Book book){
        genreCatalog.computeIfAbsent(book.getGenre(), k->new HashSet<>()).add(book);
    }

    public void removeBook(Book book){
        Set<Book> books = genreCatalog.get(book.getGenre());

        if(book!=null){
            books.remove(book);
            if (books.isEmpty()) {
                genreCatalog.remove(book.getGenre());
            }
        }
    }

    public Set<Book> bookInGenre(String genre){
        return genreCatalog.getOrDefault(genre, Collections.emptySet());
    }

    public TreeMap<Genre, Set<Book>> getAllGenres() {
        return genreCatalog;
    }

}
