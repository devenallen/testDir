package edu.uga.cs4370;

public class Movie {
    private int movieID;
    private String posterURL;
    private String title;
    private int releaseYear;
    private int movieLength;

    public Movie(int movieID, String posterURL, String title, int releaseYear, int movieLength) {
        this.movieID = movieID;
        this.posterURL = posterURL;
        this.title = title;
        this.releaseYear = releaseYear;
        this.movieLength = movieLength;
    }

    @Override
    public String toString() {
        return Integer.toString(this.movieID) + "\n" +
               this.posterURL + "\n" +
               this.title + "\n" +
               Integer.toString(this.releaseYear) + "\n" +
               Integer.toString(this.movieLength) + "\n";
    }

}
