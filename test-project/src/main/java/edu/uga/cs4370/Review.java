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

    public Review(int stars, int movieID, int userID) {
        this.stars = stars;
        this.movieID = movieID;
        this.userID = userID;
    }

    public Review() {

    }

    public void setStars(String stars) {
        try {
            this.stars = Integer.parseInt(stars);
        } catch (NumberFormatException e) {
            this.stars = 0;
        }
    }

    public void setUserID(String userID) {
        try {
            this.userID = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            this.userID = 0;
        }
    }

    public void setMovieID(String movieID) {
        try {
            this.movieID = Integer.parseInt(movieID);
        } catch (NumberFormatException e) {
            this.movieID = 0;
        }
    }

    public int getStars() {
        return this.stars;
    }

    public int getMovieID() {
        return this.movieID;
    }

    public int getUserID() {
        return this.userID;
    }
}
