package laura.portfolio.gallery_art.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
    @GetMapping("/who-i-am")
    public String whoIAm(){
        return "who-i-am";
    }
}
