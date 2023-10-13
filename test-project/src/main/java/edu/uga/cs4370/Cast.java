package edu.uga.cs4370;

public class Cast {
    /*CREATE TABLE Cast (
    CastID INT AUTO_INCREMENT PRIMARY KEY,
    ActorName VARCHAR(255),
    MovieID INT,
    FOREIGN KEY (MovieID) REFERENCES Movie(MovieID)
); */

    String name;
    int id;
    int movieID;
    
    Cast(int id, String name, int movieID) {
        this.name = name;
        this.id = id;
        this.movieID=movieID;
    }
}
