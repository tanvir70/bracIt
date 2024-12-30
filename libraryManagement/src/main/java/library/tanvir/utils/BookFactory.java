package library.tanvir.utils;

import library.tanvir.domain.Book;

public class BookFactory {
    public static Book createBook( String title, String author, int publicationYear, String genre) {
        return new Book(title, author, publicationYear, genre);
    }
}
