package kr.co.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/back")
public class BackController {

    @GetMapping("/restaurant")
    public ModelAndView restaurant(){
        return new ModelAndView("backend/restaurant/restaurant");
    }

}
