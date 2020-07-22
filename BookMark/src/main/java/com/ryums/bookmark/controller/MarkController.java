package com.ryums.bookmark.controller;

import com.ryums.bookmark.dto.MarkDTO;
import com.ryums.bookmark.service.MarkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@AllArgsConstructor
@Controller
public class MarkController {

    private MarkService markService;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @ResponseBody
    @RequestMapping("/mark/get.do")
    public Map<String, Object> getMarkList(@RequestParam Map<String, Object> param) {
        return markService.getMarkListData(param);
    }

    @RequestMapping("/mark/create")
    public ModelAndView createMarkPage() {
        return new ModelAndView("createMark", markService.getTagList());
    }

    @RequestMapping("/mark/create.do")
    public ModelAndView createMark(@RequestParam Map<String,Object> param) {
        markService.createMark(param);
        return new ModelAndView("createMark");
    }

}
