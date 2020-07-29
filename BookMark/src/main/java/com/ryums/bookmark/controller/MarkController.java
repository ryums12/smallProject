package com.ryums.bookmark.controller;

import com.ryums.bookmark.dto.MarkDTO;
import com.ryums.bookmark.service.MarkService;
import com.ryums.bookmark.utils.UtilMethod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@AllArgsConstructor
@Controller
public class MarkController {

    private MarkService markService;

    private UtilMethod utilMethod;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index", utilMethod.setType("main"));
    }

    @ResponseBody
    @RequestMapping("/mark/get.do")
    public Map<String, Object> getMarkList(@RequestParam Map<String, Object> param) {
        return markService.getMarkListData(param);
    }

    @RequestMapping("/mark/create")
    public ModelAndView createMarkPage() {
        return new ModelAndView("/mark/markCreate", markService.setMarkCreatePage());
    }

    @RequestMapping("/mark/create.do")
    public String createMark(MarkDTO markDTO) {
        markService.createMark(markDTO);
        return "redirect:/";
    }

    @RequestMapping("/mark/{markIdx}")
    public ModelAndView getMarkDetail(@PathVariable("markIdx") Long markIdx) {
        return new ModelAndView("/mark/markModal", markService.getMarkDetail(markIdx));
    }

}
