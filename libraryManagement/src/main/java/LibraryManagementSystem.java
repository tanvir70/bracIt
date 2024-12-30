import library.tanvir.Library;
import library.tanvir.domain.Book;
import library.tanvir.exceptions.BookNotFoundException;
import library.tanvir.exceptions.DuplicateBookException;
import library.tanvir.utils.BookFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {
    static Scanner scan = new Scanner(System.in);
    private static Library library = Library.getInstance();

    public static void main(String[] args) {

        while (true) {
            try {
                displayMenu();
                System.out.print("Enter your choice (1-5): ");
                int choice = scan.nextInt();

                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> removeBook();
                    case 3 -> searchBook();
                    case 4 -> displayAllBooks();
                    case 5 -> {
                        System.out.println("Exiting Library...!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a number between 1 and 5.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println(" !--------- Library Management System ----------!");
        System.out.println("1. Add a Book");
        System.out.println("2. Remove a Book");
        System.out.println("3. Search Books");
        System.out.println("4. Display All Books");
        System.out.println("5. Exit");
    }

    private static void addBook() {
        try {
            scan.nextLine();
            System.out.print("Enter title: ");
            String title = scan.nextLine();

            System.out.print("Enter Author: ");
            String author = scan.nextLine();

            System.out.print("Enter Publication Year: ");
            int year = Integer.parseInt(scan.nextLine());

            System.out.print("Enter Genre: ");
            String genre = scan.nextLine();

            Book newBook = BookFactory.createBook(title, author, year, genre);
            library.addBook(newBook);
            System.out.println("Book added successfully!");
        } catch (DuplicateBookException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void removeBook() {
        try {
            System.out.println("Press 1 to remove by Book ID");
            System.out.println("Press 2 to remove by Book Title");

            int choice = scan.nextInt();
            scan.nextLine();

            if (choice == 1) {
                System.out.println("Enter book id: ");
                int id = Integer.parseInt(scan.nextLine());
                Book removed = library.removeBook(id);
                System.out.println("Removed: " + removed);
            } else if (choice == 2) {
                System.out.println("Enter book title: ");
                String title = scan.nextLine();
                Book removed = library.removeBook(title);
                System.out.println("Removed: " + removed);
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input format. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void searchBook() {
        scan.nextLine();
        System.out.println("Enter title (or press Enter to skip): ");
        String title = scan.nextLine();

        System.out.println("Enter author (or press Enter to skip): ");
        String author = scan.nextLine();

        System.out.println("Enter Publication Year (or press Enter to skip): ");
        int publicationYear = scan.nextInt();

        ArrayList<Book> searchResult = (ArrayList<Book>) library.searchBooks(title, author, publicationYear);
        if (searchResult.isEmpty()) {
            System.out.println("No books found.");
        } else {
            displayBooks(searchResult);
        }
    }

    private static void displayBooks(ArrayList<Book> allBooks) {
        System.out.println("\n----------------------------------------------------------");
        System.out.printf("%-5s | %-20s | %-15s | %-4s | %-10s%n",
                "ID", "Title", "Author", "Year", "Genre");
        System.out.println("----------------------------------------------------------");

        for (Book book : allBooks) {
            System.out.printf("%-5d | %-20s | %-15s | %-4d | %-10s%n",
                    book.getId(),
                    limitString(book.getTitle(), 20),
                    limitString(book.getAuthor(), 15),
                    book.getPublicationYear(),
                    limitString(book.getGenre(), 10));
        }
        System.out.println("----------------------------------------------------------");
    }

    private static void displayAllBooks() {
        ArrayList<Book> allBooks = (ArrayList<Book>) library.getAllBooks();
        if (allBooks.isEmpty()) {
            System.out.println("The library is empty.");
        } else {
            displayBooks(allBooks);
        }
    }

    private static String limitString(String str, int length) {
        if (str.length() > length) {
            return str.substring(0, length - 3) + "...";
        }
        return str;
    }
}
