package com.tanvir.LibraryManagement.service;

import com.tanvir.LibraryManagement.exception.BookNotFoundException;
import com.tanvir.LibraryManagement.exception.DuplicateBookException;
import com.tanvir.LibraryManagement.factory.BookFactory;
import com.tanvir.LibraryManagement.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LibraryServiceImpl implements LibraryService {

    private static LibraryServiceImpl instance;
    private List<Book> books = new ArrayList<>();

    @Autowired
    private BookFactory bookFactory;

    private LibraryServiceImpl() {
    }

    public static synchronized LibraryServiceImpl getInstance() {
        if (instance == null) {
            instance = new LibraryServiceImpl();
        }
        return instance;
    }

    @Override
    public Book addBook(int id, String title, String author, int publicationYear, String genre) {

        if (books.stream().anyMatch((book -> book.getId() == id))) {
            throw new DuplicateBookException("Book with id " + id + " already exists");
        }

        Book book = bookFactory.createBook(id, title, author, publicationYear, genre);
        books.add(book);
        return book;
    }

    @Override
    public Book deleteBook(Integer id, String title) {
        Book book = books.stream()
                .filter(b -> (id == null || b.getId() == id) && (title == null || b.getTitle().equals(title)))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " and title " + title + " does not exist"));
        books.remove(book);
        return book;
    }

    @Override
    public List<Book> searchBook(String title, String author, Integer publicationYear, String genre) {
        return books.stream()
                .filter(book -> title == null || book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(book -> author == null || book.getAuthor().toLowerCase().equals(author.toLowerCase()))
                .filter(book -> publicationYear == null || book.getPublicationYear().equals(publicationYear))
                .filter(book -> genre == null || book.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> displayAllBooks() {
        return new ArrayList<>(books);
    }
}

