package library.tanvir;

import library.tanvir.domain.Book;
import library.tanvir.exceptions.BookNotFoundException;
import library.tanvir.exceptions.DuplicateBookException;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();

    private static Library instance;

    private Library() {}

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }


    public void addBook(Book book) throws DuplicateBookException {
        for (Book b : books) {
            if (b.getTitle() == book.getTitle()) {
                throw new DuplicateBookException("Book with Title " + book.getTitle() + " already exists");
            }
        }
        books.add(book);
    }

    public Book removeBook(int id) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getId() == id) {
                books.remove(book);
                return book;
            }
        }
        throw new BookNotFoundException("Book with id " + id + " not found");
    }

    public Book removeBook(String title) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                books.remove(book);
                return book;
            }
        }
        throw new BookNotFoundException("Book with title " + title + " not found");
    }

    public List<Book> searchBooks(String title, String author, int publicationYear) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if ((title == null || book.getTitle().toLowerCase().contains(title.toLowerCase())) &&
                    (author == null || book.getAuthor().toLowerCase().contains(author.toLowerCase())) &&
                    (publicationYear == 0 || book.getPublicationYear() == publicationYear)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}
