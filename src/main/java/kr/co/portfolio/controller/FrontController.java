package kr.co.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class FrontController {

    @GetMapping("/starbucks")
    public String starbucks(){
        return "starbucks";
    }

    @GetMapping("/signin")
    public String starbucksLogin(){
        return "signin/signin";
    }

    @GetMapping("/overwatch")
    public String overwatch(){
        return "overwatch";
    }
}
