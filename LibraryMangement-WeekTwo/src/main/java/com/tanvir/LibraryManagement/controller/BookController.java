package com.tanvir.LibraryManagement.controller;

import com.tanvir.LibraryManagement.exception.BookNotFoundException;
import com.tanvir.LibraryManagement.model.Book;
import com.tanvir.LibraryManagement.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/library/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBook(@RequestBody Book book) {

        Book addedBook = libraryService.addBook(book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getGenre());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book added successfully");
        response.put("book", addedBook);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteBook(@RequestParam(required = false) Integer id,
                                        @RequestParam(required = false) String title) {
        if (title == null && id == null) {
            throw new IllegalArgumentException("Please provide at least one search criteria");
        }
        Book deletebook = libraryService.deleteBook(id, title);
        if (deletebook == null) {
            throw new BookNotFoundException("No books found with the given search criteria");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book deleted successfully");
        response.put("book", deletebook);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<?> searchBook(@RequestParam(required = false) String title,
                                        @RequestParam(required = false) String author,
                                        @RequestParam(required = false) Integer publicationYear,
                                        @RequestParam(required = false) String genre) {
        if (title == null && author == null && publicationYear == null && genre == null) {
            throw new IllegalArgumentException("Please provide at least one search criteria");
        }

        List<Book> books = libraryService.searchBook(title, author, publicationYear, genre);
        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found with the given search criteria");
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> displayAllBooks() {

        List<Book> books = libraryService.displayAllBooks();
        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found");
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
