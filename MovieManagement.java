class Movie {
    String title;
    String director;
    int year;
    float rating;
    Movie next;
    Movie prev;

    public Movie(String title, String director, int year, float rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieList {
    private Movie head;
    private Movie tail;

    public MovieList() {
        head = null;
        tail = null;
    }

    public void addMovie(String title, String director, int year, float rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void deleteMovie(String title) {
        if (head == null) return;

        Movie temp = head;
        while (temp != null) {
            if (temp.title.equals(title)) {
                if (temp.prev != null) {
                    temp.prev.next = temp.next;
                } else {
                    head = temp.next;
                }
                if (temp.next != null) {
                    temp.next.prev = temp.prev;
                } else {
                    tail = temp.prev;
                }
                return;
            }
            temp = temp.next;
        }
    }

    public Movie searchMovie(String director, Float rating) {
        Movie temp = head;
        while (temp != null) {
            if ((director != null && temp.director.equals(director)) || (rating != null && temp.rating == rating)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void displayMoviesForward() {
        Movie temp = head;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year + ", Rating: " + temp.rating);
            temp = temp.next;
        }
    }

    public void displayMoviesBackward() {
        Movie temp = tail;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director + ", Year: " + temp.year + ", Rating: " + temp.rating);
            temp = temp.prev;
        }
    }
}

// Example usage
public class MovieManagement {
    public static void main(String[] args) {
        MovieList movieList = new MovieList();
        movieList.addMovie("Inception", "Christopher Nolan", 2010, 8.8f);
        movieList.addMovie("Titanic", "James Cameron", 1997, 7.8f);
        movieList.displayMoviesForward();
        movieList.deleteMovie("Titanic");
        movieList.displayMoviesForward();
        movieList.displayMoviesBackward();
    }
}