package com.example.Library.Control.Sort.BookSort;

import com.example.Library.Model.Book;
import org.springframework.stereotype.Controller;

import java.util.Comparator;

@Controller
public class BookSortByPublicationYear implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        return Integer.compare(o1.getPublicationYear(), o2.getPublicationYear());
    }
}
