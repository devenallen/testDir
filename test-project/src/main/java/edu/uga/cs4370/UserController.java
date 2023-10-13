package edu.uga.cs4370;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @GetMapping("/login")
    public String showLoginForm(){
        return "login-form";
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email){
        //login logic to check if user already exists
        //if yes, log user in
        return "redirect:/homepage";
    }
    @GetMapping("/signup")
    public String showSignUpForm(){
        return "signup-form";
    }
    @PostMapping("/signup")
    public String signupUser(@RequestParam("email") String email){
        //sign up logic will go here
        //once user is signed up, then they're directed to the homepage
        return "redirect:/homepage";
    }
}
