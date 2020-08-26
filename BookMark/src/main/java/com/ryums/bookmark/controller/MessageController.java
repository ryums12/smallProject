package com.ryums.bookmark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {

    @RequestMapping("/msg")
    public ModelAndView message() {
        return new ModelAndView("/alert");
    }
}
