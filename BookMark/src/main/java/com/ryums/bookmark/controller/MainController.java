package com.ryums.bookmark.controller;

import com.ryums.bookmark.dto.MarkDTO;
import com.ryums.bookmark.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
public class MainController {

    private MainService mainService;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/mark/create/page")
    public ModelAndView createMarkPage() {
        return new ModelAndView("createMark");
    }

    @RequestMapping("/mark/create.do")
    public ModelAndView createMark(MarkDTO markDTO) {
        mainService.createMark(markDTO);
        return new ModelAndView("createMark");
    }

}
