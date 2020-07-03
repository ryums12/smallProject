package com.ryums.bookmark.controller;

import com.ryums.bookmark.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

}
