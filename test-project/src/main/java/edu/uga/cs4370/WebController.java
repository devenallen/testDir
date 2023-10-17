package edu.uga.cs4370;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// import org.json.JSONObject;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



@Controller
public class WebController {
    int UserID;
    int MovieID;
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
            // connection.close();
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

    @PostMapping("/removeReview")
    @ResponseBody
    public ModelAndView removeReview(@RequestParam("id")int id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE from Review WHERE ReviewID=" +id);            
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return new ModelAndView("redirect:/MyReviews");
    }

    @PostMapping("/enterReview")
    @ResponseBody
    public ModelAndView enterReview(@RequestParam("rating") int stars) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Review (StarCount, MovieID, UserID) VALUES (" + stars + ", " +MovieID +", "+UserID+")");            
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return new ModelAndView("redirect:/MyReviews");
    }

    @GetMapping("/PerMovie")
    @ResponseBody
    public ModelAndView perMovie() {
        ModelAndView mv = new ModelAndView("PerMovie");
        Movie mvs = new Movie(null,null,0,0,0);
        List <String> ls = new ArrayList<>();
        String sql = "select M.Title, M.Poster, M.ReleaseYear, M.MovieLength, C.ActorName from Movie M join Cast C on M.MovieID = C.MovieID WHERE M.MovieID="+MovieID;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                mvs = new Movie(rs.getString("Title"),
                rs.getString("Poster"),
                MovieID, 
                rs.getInt("ReleaseYear"),
                rs.getInt("MovieLength"));
                System.out.println(mvs.name);
                ls.add(rs.getString("ActorName"));
            }//All the review ids of the current user
        } catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        mv.addObject("mac", mvs);
        mv.addObject("actors", ls);
        return mv;
    }

    @GetMapping("/MyReviews") 
    @ResponseBody
    public ModelAndView myReviews() {
        ModelAndView mv = new ModelAndView("MyReviews");
        List <MyReviews> mr = new ArrayList<>();
        String sql = "select R.ReviewID, R.StarCount, M.Title from Review R join User U on R.UserID = U.UserID join Movie M on R.MovieID = M.MovieID WHERE U.UserID =" + UserID + " ORDER BY R.StarCount desc";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                mr.add(new MyReviews(rs.getInt("ReviewID"),rs.getString("Title"), rs.getInt("StarCount")));
                
            }//All the review ids of the current user
        } catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
       mv.addObject("review", mr);
        return mv;
    }

    @PostMapping("/GetMovie")
    @ResponseBody
    public ModelAndView getMovie(@RequestParam("id") Integer id) {
        System.out.println(id);
        MovieID = id;
        return new ModelAndView("redirect:/PerMovie");
    }

    @GetMapping("/login")
    @ResponseBody
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("Login");
        return mv;
    }

    @PostMapping("/addUser")
    @ResponseBody
    public ModelAndView addUser(@RequestBody String email) {
        try {
            String sql = "SELECT * FROM User WHERE Username='" + email +"'"; 
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO User (Username) VALUES ( '" + email + "' )");
                resultSet = preparedStatement.executeQuery();
            }
            UserID = resultSet.getInt("UserID");
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return new ModelAndView("redirect:/");
    }
}
