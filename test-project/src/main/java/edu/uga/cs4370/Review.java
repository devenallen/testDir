package edu.uga.cs4370;

public class Review {
    /*
     * CREATE TABLE Review (
    ReviewID INT AUTO_INCREMENT PRIMARY KEY,
    StarCount INT,
    MovieID INT,
    UserID INT,
    FOREIGN KEY (MovieID) REFERENCES Movie(MovieID),
    FOREIGN KEY (UserID) REFERENCES User(UserID)
);
     */
    public int id;
    public int stars;
    public int movieID;
    public int userID;

    Review(int id, int stars, int movieID, int userID) {
        this.id = id;
        this.stars = stars;
        this.movieID = movieID;
        this.userID = userID;
    }
}
