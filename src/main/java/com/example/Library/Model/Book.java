package com.example.Library.Model;


import com.example.Library.Enum.BookEnum.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private  String ISBN;
    private String author;
    private String title;
    private int publicationYear;
    private Genre genre;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return ISBN == book.ISBN && Objects.equals(publicationYear,book.publicationYear) && Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, author, title, publicationYear, genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN=" + ISBN +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", genre='" + genre + '\'' +
                '}';
    }
}
