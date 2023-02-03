package kr.co.portfolio.chatGpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/back")
public class ChatgptController {

    @GetMapping("/chatgpt")
    public ModelAndView chatMain(){
        return new ModelAndView("backend/chatgpt/chatgpt");
    }

}
