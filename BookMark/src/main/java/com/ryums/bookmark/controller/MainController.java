package com.ryums.bookmark.controller;

import com.ryums.bookmark.dto.MarkDTO;
import com.ryums.bookmark.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@AllArgsConstructor
@Controller
public class MainController {

    private MainService mainService;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @ResponseBody
    @RequestMapping("/mark/get.do")
    public Map<String, Object> getMarkList(@RequestParam Map<String, Object> param) {
        return mainService.getMarkListData(param);
    }

    @RequestMapping("/mark/create")
    public ModelAndView createMarkPage() {
        return new ModelAndView("createMark");
    }

    @RequestMapping("/mark/create.do")
    public ModelAndView createMark(MarkDTO markDTO) {
        mainService.createMark(markDTO);
        return new ModelAndView("createMark");
    }

    @RequestMapping("/mark/create/tag")
    public ModelAndView createTagPage() {
        return new ModelAndView("createTag");
    }

}
