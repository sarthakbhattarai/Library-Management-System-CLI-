package com.example.Library.Control.Sort.BookSort;

import com.example.Library.Model.Book;
import org.springframework.stereotype.Controller;

import java.util.Comparator;

@Controller
public class BookSortByTitle  implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
