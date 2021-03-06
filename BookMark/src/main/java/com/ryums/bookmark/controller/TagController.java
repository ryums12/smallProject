package com.ryums.bookmark.controller;

import com.ryums.bookmark.dto.TagDTO;
import com.ryums.bookmark.service.TagService;
import com.ryums.bookmark.utils.UtilMethod;
import com.ryums.bookmark.utils.files.StorageService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class TagController {

    private TagService tagService;
    private StorageService storageService;
    private UtilMethod utilMethod;
    private ModelMapper modelMapper;

    @RequestMapping("/tag/create")
    public ModelAndView createTagPage() {
        return new ModelAndView("/tag/tagCreate", utilMethod.setType("tag"));
    }

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public void createTag(@Validated TagDTO tagDTO, BindingResult bindingResult,
                          MultipartHttpServletRequest fileRequest,
                          HttpServletRequest request, HttpServletResponse response) {

        try {
            String msg = "";
            String href = "";
            
            if(bindingResult.hasErrors()) {
                List<ObjectError> errorList = bindingResult.getAllErrors();

                msg = errorList.get(0).getDefaultMessage();
                href = "back";
            } else {
                MultipartFile file = fileRequest.getFile("tagImg");
                String fileName = file.getOriginalFilename();

                if(file != null) {
                    Map<String, Object> tempObj = tagService.setTagDTO(tagDTO, fileName);
                    tagDTO = modelMapper.map(tempObj.get("tagDto"), TagDTO.class);
                    storageService.store(file, (String) tempObj.get("imgName"));
                }

                tagService.createTag(tagDTO);

                msg = "저장 되었습니다";
                href = "/";
            }

            request.setAttribute("msg", msg);
            request.setAttribute("nextPage", href);
            request.getRequestDispatcher("/msg").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/tag",method = RequestMethod.GET)
    public Map<String, Object> getTagList(@RequestParam Map<String, Object> param) {
        return tagService.getTagList(param);
    }

    @RequestMapping("/tag/list")
    public ModelAndView tagListPage() {
        return new ModelAndView("/tag/tagList", utilMethod.setType("tag"));
    }

    @RequestMapping(value = "/tag/{tagIdx}", method = RequestMethod.GET)
    public ModelAndView getTagDetail(@PathVariable("tagIdx") Long tagIdx) {
        return new ModelAndView("/tag/tagModal", tagService.getTagDetail(tagIdx));
    }
}
