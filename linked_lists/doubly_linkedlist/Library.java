import java.util.*;

class Book {
    String title;
    String author;
    String genre;
    int bookID;
    boolean availabilityStatus;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, int bookID, boolean availabilityStatus) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookID = bookID;
        this.availabilityStatus = availabilityStatus;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    Book head;
    Book tail;
    int totalBooks;

    public Library() {
        head = null;
        tail = null;
        totalBooks = 0;
    }

    public void addBookAtBeginning(String title, String author, String genre, int bookID, boolean availabilityStatus) {
        Book newBook = new Book(title, author, genre, bookID, availabilityStatus);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        totalBooks++;
    }

    public void addBookAtEnd(String title, String author, String genre, int bookID, boolean availabilityStatus) {
        Book newBook = new Book(title, author, genre, bookID, availabilityStatus);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        totalBooks++;
    }

    public void addBookAtPosition(String title, String author, String genre, int bookID, boolean availabilityStatus, int position) {
        Book newBook = new Book(title, author, genre, bookID, availabilityStatus);
        if (position == 0) {
            addBookAtBeginning(title, author, genre, bookID, availabilityStatus);
            return;
        }
        Book current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) return;
        newBook.next = current.next;
        newBook.prev = current;
        if (current.next != null) {
            current.next.prev = newBook;
        }
        current.next = newBook;
        if (newBook.next == null) {
            tail = newBook;
        }
        totalBooks++;
    }

    public void removeBookByID(int bookID) {
        Book current = head;
        while (current != null) {
            if (current.bookID == bookID) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                totalBooks--;
                return;
            }
            current = current.next;
        }
    }

    public Book searchBookByTitle(String title) {
        Book current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public Book searchBookByAuthor(String author) {
        Book current = head;
        while (current != null) {
            if (current.author.equalsIgnoreCase(author)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void updateBookAvailabilityStatus(int bookID, boolean newStatus) {
        Book current = head;
        while (current != null) {
            if (current.bookID == bookID) {
                current.availabilityStatus = newStatus;
                return;
            }
            current = current.next;
        }
    }

    public void displayBooksForward() {
        if (head == null) {
            System.out.println("No books in the library.");
            return;
        }
        Book current = head;
        while (current != null) {
            System.out.println("Title: " + current.title + ", Author: " + current.author + ", Genre: " + current.genre + ", Book ID: " + current.bookID + ", Availability: " + (current.availabilityStatus ? "Available" : "Not Available"));
            current = current.next;
        }
    }

    public void displayBooksReverse() {
        if (tail == null) {
            System.out.println("No books in the library.");
            return;
        }
        Book current = tail;
        while (current != null) {
            System.out.println("Title: " + current.title + ", Author: " + current.author + ", Genre: " + current.genre + ", Book ID: " + current.bookID + ", Availability: " + (current.availabilityStatus ? "Available" : "Not Available"));
            current = current.prev;
        }
    }

    public int countTotalBooks() {
        return totalBooks;
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBookAtBeginning("The Catcher in the Rye", "J.D. Salinger", "Fiction", 101, true);
        library.addBookAtEnd("1984", "George Orwell", "Dystopian", 102, true);
        library.addBookAtEnd("To Kill a Mockingbird", "Harper Lee", "Fiction", 103, false);
        library.addBookAtPosition("Moby Dick", "Herman Melville", "Adventure", 104, true, 2);

        System.out.println("Books in Forward Order:");
        library.displayBooksForward();

        System.out.println("\nBooks in Reverse Order:");
        library.displayBooksReverse();

        System.out.println("\nTotal number of books in the library: " + library.countTotalBooks());

        library.removeBookByID(102);
        System.out.println("\nBooks after removing book with ID 102:");
        library.displayBooksForward();

        library.updateBookAvailabilityStatus(103, true);
        System.out.println("\nBooks after updating availability status of book with ID 103:");
        library.displayBooksForward();

        Book foundBook = library.searchBookByTitle("1984");
        if (foundBook != null) {
            System.out.println("\nFound book by title '1984': " + foundBook.title);
        } else {
            System.out.println("\nNo book found with title '1984'.");
        }

        foundBook = library.searchBookByAuthor("Harper Lee");
        if (foundBook != null) {
            System.out.println("\nFound book by author 'Harper Lee': " + foundBook.title);
        } else {
            System.out.println("\nNo book found by author 'Harper Lee'.");
        }
    }
}
