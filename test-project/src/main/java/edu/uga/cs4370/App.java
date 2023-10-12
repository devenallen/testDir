package edu.uga.cs4370;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

@SpringBootApplication
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String url = "jdbc:mysql://localhost:33306/riggedDB";
        String username = "root";
        String password = "mysqlpass";

        System.out.println("Connecting to the database...");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed!");
        }
        Statement statement = connection.createStatement();

        //Checking if a user is in the database
        String user = "PreetamJain";
        String sql = "SELECT Username FROM User WHERE User.Username = '" + user + "'";
        ResultSet resultSet;

        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next() != true) {
                System.out.println("Invalid user!");
            } else {
                System.out.println("User: " + resultSet.getString("Username"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Getting movies
        sql = "SELECT * FROM MOVIE";
        resultSet = statement.executeQuery(sql);
        ArrayList<Movie> movies = new ArrayList<Movie>();
        while (resultSet.next() != false) {
            Movie newMovie = new Movie(resultSet.getInt("MovieID"), resultSet.getString("Poster"), 
                                       resultSet.getString("Title"), resultSet.getInt("ReleaseYear"), 
                                       resultSet.getInt("MovieLength"));
            movies.add(newMovie);
            System.out.println("Movie: " + newMovie + "\n");
        }

        //Getting reviews from a particular movie
        String movie = "Captain America: The First Avenger";
        sql = "SELECT StarCount, Username, Title FROM Review JOIN Movie ON Review.MovieID = Movie.MovieID JOIN User ON Review.UserID = User.UserID WHERE Movie.Title = '" + movie + "'";
        try {
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        while(resultSet.next() != false) {
            System.out.println("Movie: " + resultSet.getString("Title"));
            System.out.println("User: " + resultSet.getString("Username"));
            System.out.println(resultSet.getInt("StarCount") + " Stars");
        }

        //Adding a review, user, and movie to the database
        User ronaldoUser = new User(5, "CristianoRonaldo");
        Movie harryPotter = new Movie(103, "https://m.media-amazon.com/images/M/MV5BNmQ0ODBhMjUtNDRhOC00MGQzLTk5MTAtZDliODg5NmU5MjZhXkEyXkFqcGdeQXVyNDUyOTg3Njg@._V1_.jpg", 
                                        "Harry Potter and the Sorcerer's Stone", 2001, 152); 
        Cast harryPotterCast = new Cast("Daniel Radcliffe", 103);
        Review harryPotterReview = new Review(5, 103, 5);
        int stars = harryPotterReview.getStars();
        int movieID = harryPotterReview.getMovieID();
        int userID = harryPotterReview.getUserID();

        sql = "INSERT INTO User VALUES(" + movieID + "," + userID + ")";
        sql = "INSERT INTO Movie VALUES(" + stars + "," + movieID + "," + userID + ")";
        sql = "INSERT INTO Cast VALUES(" + stars + "," + movieID + "," + userID + ")";
        sql = "INSERT INTO Review VALUES(" + stars + "," + movieID + "," + userID + ")";
    }
}