package com.example.Library.CLI;


import com.example.Library.Enum.BookEnum.Genre;
import com.example.Library.Enum.UserEnum.MembershipType;
import com.example.Library.Model.Book;
import com.example.Library.Model.User;
import com.example.Library.Repository.Library;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Scanner;

@SpringBootApplication
public class Main {
    private Library library;
    private Scanner scanner;

    public Main() {
        this.library = Library.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Publication Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Genre (FICTION, NON_FICTION, MYSTERY, SCIENCE, HISTORY, FANTASY): ");
        Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase());

        Book book = new Book(ISBN, title, author, year, genre);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }

    private void registerUser() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Membership Type (REGULAR,STANDARD, PREMIUM): ");
        MembershipType membershipType = MembershipType.valueOf(scanner.nextLine().toUpperCase());

        User user = new User(userId, name, email, membershipType);
        library.registerUser(user);
        System.out.println("User registered successfully.");
    }

    private void borrowBook() {
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        User user = library.getUserManage().getUser(userId);
        if (user != null) {
            library.borrowBook(ISBN, user);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void returnBook() {
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        User user = library.getUserManage().getUser(userId);
        if (user != null) {
            library.returnBook(ISBN, user);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public static void main(String[] args) {
        Main cli = new Main();
        cli.start();
    }
}
