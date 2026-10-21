package librarysystem.model;

import librarysystem.interfaces.Reservable;

public class Book implements Reservable {
    private final String bookId;
    private final String title;
    private final String author;
    private boolean available;

    public Book(String bookId, String title, String author) {
        if (bookId == null || bookId.isBlank()) {
            throw new IllegalArgumentException("Book ID is required.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required.");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author is required.");
        }

        this.bookId = bookId.trim();
        this.title = title.trim();
        this.author = author.trim();
        this.available = true;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public boolean reserveBook(String bookId) {
        if (bookId == null || bookId.isBlank()) {
            System.out.println("Reservation failed: Book ID is required.");
            return false;
        }

        if (!this.bookId.equalsIgnoreCase(bookId.trim())) {
            System.out.println("Reservation failed: Book ID does not match this book.");
            return false;
        }

        if (!available) {
            System.out.println("Reservation failed: Book " + this.bookId + " is already unavailable.");
            return false;
        }

        available = false;
        System.out.println("Reservation successful: Book " + this.bookId + " has been reserved.");
        return true;
    }
}