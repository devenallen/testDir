package edu.uga.cs4370;

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

@Controller
@RequestMapping("dynamic")
public class WebController {

    List<Book> books;

    public WebController() {
        books = new ArrayList<>();
        books.add(new Book("Title 1", "Author 1", 2002));
        books.add(new Book("Title 2", "Author 2", 2012));
        books.add(new Book("Title 3", "Author 3", 2013));
        books.add(new Book("Title 4", "Author 4", 2015));
        books.add(new Book("Title 4", "Author 4", 2015));
        books.add(new Book("Title 4", "Author 4", 2015));
        books.add(new Book("Title 4", "Author 4", 2023));
    }

    @GetMapping("/hello")
    @ResponseBody
    public String helloEndpoint() {
        return "<html><body><h1>"
                    + new Random().nextInt(100) 
                    +"</h1></body></html>";
    }

    @GetMapping("/webpage")
    public ModelAndView webpage() {
        ModelAndView mv = new ModelAndView("hellotemplate");
        String message = "DB class 4370 " + new Random().nextInt(100);
        mv.addObject("message", message);
        mv.addObject("books", books);
        return mv;
    }

    @PostMapping("/submitform")
    public String formsubmit(@ModelAttribute Book book) {
        books.add(book);
        return "redirect:/dynamic/webpage";
    }


}
