package com.example.Library.Control.Sort.BookSort;

import com.example.Library.Model.Book;
import org.springframework.stereotype.Controller;

import java.util.Comparator;

@Controller
public class BookSortByAuthor implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getAuthor().compareTo(o2.getAuthor());
    }
}
