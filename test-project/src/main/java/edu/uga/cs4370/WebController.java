package edu.uga.cs4370;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class WebController {

    private final String url = "jdbc:mysql://localhost:33306/riggedDB";
    private final String user = "root";
    private final String password = "mysqlpass";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public WebController() {
        System.out.println("Connecting to the database...");
        try {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Connected successfully!");
            if (this.connection != null && this.connection.isValid(2)) { 
                this.statement = this.connection.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed!");
        }
    }

    @GetMapping
    public ModelAndView homePage() {
        ModelAndView mv = new ModelAndView("homepagetemplate");
        return mv;
    }

    @GetMapping("/addreview")
    public ModelAndView addReview() {
        ModelAndView mv = new ModelAndView("addreviewtemplate");
        return mv;
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView("signuptemplate");
        return mv;
    }

    @PostMapping("/submitusername") 
    public String submitUsername(@RequestParam String username, RedirectAttributes redirectAttributes) {
        String sql = "SELECT Username FROM User WHERE Username = \"" + username + "\"";
        String error = "User already exists!";
        try {
            resultSet = statement.executeQuery(sql);
            if (resultSet.next() == true) {
                redirectAttributes.addFlashAttribute("error", error);
                return "redirect:/signup";
            } else {
                sql = "INSERT INTO User (Username) VALUES(\"" + username  + "\")";
                try {
                    statement.executeUpdate(sql);
                }  catch (SQLException e) {
                    System.out.println(e.getMessage());
                    redirectAttributes.addFlashAttribute("error", "Database error. Please try again.");
                    return "redirect:/signup";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/login";
    }

    //@GetMapping("/login")

    @PostMapping("/submitform")
    public String formsubmit(@ModelAttribute Review review, RedirectAttributes redirectAttributes) {
        String validationError = validateReview(review);
        if (validationError != null) {
            redirectAttributes.addAttribute("error", validationError);
            return "redirect:/dynamic/webpage";
        }

        String sql = "INSERT INTO Review VALUES(" + review.getStars() + "," + review.getMovieID() + "," + review.getUserID() + ")";
        try {
            statement.executeUpdate(sql);
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Database error. Please try again.");
            return "redirect:/dynamic/webpage";
        } 
        return "redirect:/dynamic/webpage";
    }

    public String validateReview(Review review) {
        if (review.getStars() < 1 || review.getStars() > 5) {
            return "Stars must be between 1 and 5";
        }
        return null;
    }
}
