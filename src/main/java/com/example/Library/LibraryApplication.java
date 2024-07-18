package com.example.Library;

import com.example.Library.Control.BookController.BookCatalog;
import com.example.Library.Model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import static com.example.Library.Enum.BookEnum.Genre.*;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {

		ApplicationContext context=SpringApplication.run(LibraryApplication.class, args);
		BookCatalog bookCat=context.getBean(BookCatalog.class);
		Book bookCp= context.getBean(Book.class);

		bookCat.addBook(new Book("AB_12","Sarthak","Romeo and julite",2023,FICTION));
		bookCat.addBook(new Book("AB_13","Ram","love life",2022 ,MYSTERY));
		bookCat.addBook(new Book("AB_14","Hari","test you",2020 ,SCIENCE));
		bookCat.addBook(new Book("AB_15","Shyam","Happy",2019 ,HISTORY));

		System.out.println(bookCat.getAllBook());
		System.out.println(bookCat.getBook("AB_15"));
		bookCat.removeBook("AB_15");
		System.out.println(bookCat.getAllBook());
		System.out.println(bookCp.hashCode());
		System.out.println();

		System.out.println();
		System.out.println(bookCat.searchByAuthor("Hari"));

	}

}
