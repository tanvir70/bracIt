package com.tanvir.LibraryManagement.service;

import com.tanvir.LibraryManagement.model.Book;

import java.util.List;


public interface LibraryService {
    Book addBook(int id, String title, String author, int publicationYear, String genre);

    Book deleteBook(Integer bookId, String title);

    List<Book> searchBook(String title, String author, Integer publicationYear, String genre);

    List<Book> displayAllBooks();
}
