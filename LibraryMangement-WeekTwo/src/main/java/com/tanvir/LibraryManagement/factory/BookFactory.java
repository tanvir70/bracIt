package com.tanvir.LibraryManagement.factory;

import com.tanvir.LibraryManagement.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookFactory {
    public Book createBook(int id, String title, String author, int publicationYear, String genre) {
        return new Book(id, title, author, publicationYear, genre);
    }
}
