import java.util.*;

class Movie {
    String title;
    String director;
    int yearOfRelease;
    double rating;
    Movie next;
    Movie prev;

    public Movie(String title, String director, int yearOfRelease, double rating) {
        this.title = title;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieList {
    Movie head;
    Movie tail;

    public MovieList() {
        head = null;
        tail = null;
    }

    public void addMovieAtBeginning(String title, String director, int yearOfRelease, double rating) {
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    public void addMovieAtEnd(String title, String director, int yearOfRelease, double rating) {
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void addMovieAtPosition(String title, String director, int yearOfRelease, double rating, int position) {
        Movie newMovie = new Movie(title, director, yearOfRelease, rating);
        if (position == 0) {
            addMovieAtBeginning(title, director, yearOfRelease, rating);
            return;
        }
        Movie current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) return;
        newMovie.next = current.next;
        newMovie.prev = current;
        if (current.next != null) {
            current.next.prev = newMovie;
        }
        current.next = newMovie;
        if (newMovie.next == null) {
            tail = newMovie;
        }
    }

    public void removeMovieByTitle(String title) {
        Movie current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
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
                return;
            }
            current = current.next;
        }
    }

    public Movie searchMovieByDirector(String director) {
        Movie current = head;
        while (current != null) {
            if (current.director.equalsIgnoreCase(director)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public Movie searchMovieByRating(double rating) {
        Movie current = head;
        while (current != null) {
            if (current.rating == rating) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void updateMovieRating(String title, double newRating) {
        Movie current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                current.rating = newRating;
                return;
            }
            current = current.next;
        }
    }

    public void displayMoviesForward() {
        if (head == null) {
            System.out.println("No movies in the list.");
            return;
        }
        Movie current = head;
        while (current != null) {
            System.out.println("Title: " + current.title + ", Director: " + current.director + ", Year: " + current.yearOfRelease + ", Rating: " + current.rating);
            current = current.next;
        }
    }

    public void displayMoviesReverse() {
        if (tail == null) {
            System.out.println("No movies in the list.");
            return;
        }
        Movie current = tail;
        while (current != null) {
            System.out.println("Title: " + current.title + ", Director: " + current.director + ", Year: " + current.yearOfRelease + ", Rating: " + current.rating);
            current = current.prev;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MovieList movieList = new MovieList();

        movieList.addMovieAtBeginning("Inception", "Christopher Nolan", 2010, 8.8);
        movieList.addMovieAtEnd("The Dark Knight", "Christopher Nolan", 2008, 9.0);
        movieList.addMovieAtEnd("Interstellar", "Christopher Nolan", 2014, 8.6);
        movieList.addMovieAtPosition("Dunkirk", "Christopher Nolan", 2017, 7.9, 2);

        System.out.println("Movies in Forward Order:");
        movieList.displayMoviesForward();

        System.out.println("\nMovies in Reverse Order:");
        movieList.displayMoviesReverse();

        movieList.removeMovieByTitle("The Dark Knight");
        System.out.println("\nMovies after removing 'The Dark Knight':");
        movieList.displayMoviesForward();

        movieList.updateMovieRating("Inception", 9.0);
        System.out.println("\nMovies after updating rating of 'Inception':");
        movieList.displayMoviesForward();

        Movie foundMovie = movieList.searchMovieByDirector("Christopher Nolan");
        if (foundMovie != null) {
            System.out.println("\nFound movie by director 'Christopher Nolan': " + foundMovie.title);
        } else {
            System.out.println("\nNo movie found by director 'Christopher Nolan'.");
        }
    }
}
