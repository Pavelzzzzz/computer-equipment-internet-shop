package by.pavelzzzzz.spring.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/{pageName}")
    public String redirectToPage(@PathVariable String pageName){
        return "frontEnd/jsp/" + pageName;
    }

    @GetMapping("/")
    public String redirectToIndex(){
        return "frontEnd/jsp/index";
    }

    @GetMapping("/administration")
    public String redirectToAdminPage(String pageName){
        return "frontEnd/jsp/administration/" + pageName;
    }
}
