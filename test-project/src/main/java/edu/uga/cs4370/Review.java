package edu.uga.cs4370;

public class Review {
    private int reviewID;
    private int stars;
    private int movieID;
    private int userID;

    public Review(int reviewID, int stars, int movieID, int userID) {
        this.reviewID = reviewID;
        this.stars = stars;
        this.movieID = movieID;
        this.userID = userID;
    }
}
