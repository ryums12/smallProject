package com.ryums.bookmark.controller;

import com.ryums.bookmark.dto.MarkDTO;
import com.ryums.bookmark.service.MarkService;
import com.ryums.bookmark.utils.UtilMethod;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
    @RequestMapping(value = "/mark", method = RequestMethod.GET)
    public Map<String, Object> getMarkList(@RequestParam Map<String, Object> param) {
        return markService.getMarkListData(param);
    }

    @RequestMapping("/mark/create")
    public ModelAndView createMarkPage() {
        return new ModelAndView("/mark/markCreate", markService.setMarkCreatePage());
    }

    @RequestMapping(value = "/mark", method = RequestMethod.POST)
    public void saveMark(@Validated MarkDTO markDTO, BindingResult bindingResult,
                           HttpServletRequest request, HttpServletResponse response) {

        try {
            String msg = "";
            String href = "";

            if (bindingResult.hasErrors()) {
                List<ObjectError> errorList = bindingResult.getAllErrors();

                msg = errorList.get(0).getDefaultMessage();
                href = "back";
            } else {
                markService.saveMark(markDTO);

                msg = "저장 되었습니다";
                href = "/";
            }

            request.setAttribute("msg",msg);
            request.setAttribute("nextPage", href);
            request.getRequestDispatcher("/msg").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/mark/{markIdx}", method = RequestMethod.GET)
    public ModelAndView getMarkDetail(@PathVariable("markIdx") Long markIdx) {
        return new ModelAndView("/mark/markModal", markService.getMarkDetail(markIdx));
    }

    @RequestMapping("/mark/unused")
    public ModelAndView getUnusedMarkList() {
       return new ModelAndView("/mark/markUnUsedList", utilMethod.setType("unused"));
    }
}
