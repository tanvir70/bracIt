package library.tanvir.domain;

public class Book {

    private static int nextId = 1;

    private int id;
    private String title;
    private String author;
    private int publicationYear;
    private String genre;

    public Book(String title, String author, int publicationYear, String genre) {
        this.id = nextId;
        nextId++;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getGenre() {
        return genre;
    }
}
