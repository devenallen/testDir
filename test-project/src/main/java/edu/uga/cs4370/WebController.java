package edu.uga.cs4370;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



@Controller
public class WebController {
    List <Movie> movies;
    List <Review> reviews;
    List <Cast> casts;
    Connection connection;
    public WebController() {
        movies = new ArrayList<>();
        reviews = new ArrayList<>();
        casts = new ArrayList<>();
        String jdbcUrl = "jdbc:mysql://localhost:33306/riggedDB";
        String username = "root";
        String password = "bathinda";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection secured");
            //Movie
            String sql = "SELECT * FROM Movie"; 
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie(resultSet.getString("Title"),
                resultSet.getString("Poster"),
                resultSet.getInt("MovieID"), 
                resultSet.getInt("ReleaseYear"),
                resultSet.getInt("MovieLength"));
                movies.add(movie);
            }
            //Reviews
            sql = "SELECT * FROM Review";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = new Review(resultSet.getInt("ReviewID"),
                resultSet.getInt("StarCount"),
                resultSet.getInt("MovieID"),
                resultSet.getInt("UserID"));
                reviews.add(review);
            }
            //Cast
            sql = "SELECT * FROM Cast";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cast cast = new Cast(resultSet.getInt("CastID"),
                resultSet.getString("ActorName"),
                resultSet.getInt("MovieID"));
                casts.add(cast);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/addMovie")
    @ResponseBody
    public void addMovie(@RequestBody Movie movie) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Movie VALUES(" + movie.id + ", "  + movie.url + ", "+ movie.name + ", " + movie.year + ", " + movie.length);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        movies.add(movie);
    }

    @PostMapping("/addCast")
    @ResponseBody
    public void addCast(@RequestBody Cast cast) {
        casts.add(cast);
    }

    @PostMapping("/addReview")
    @ResponseBody
    public void addReview(@RequestBody Review review) {
        reviews.add(review);
    }

    @GetMapping("/movies")
    @ResponseBody
    public List<Movie> getMovies() {
        return movies;
    }

    @GetMapping("/")
    @ResponseBody
    public ModelAndView homePage() {
        ModelAndView hp = new ModelAndView("HomePage");
        hp.addObject("movies", movies);
        return hp;
    }

    @GetMapping("/reviews")
    @ResponseBody
    public List<Review> getReviews() {
        return reviews;
    }

    @GetMapping("/cast")
    @ResponseBody
    public List<Cast> getCast() {
        return casts;
    }
}
