package com.ryums.bookmark.controller;

import com.ryums.bookmark.dto.TagDTO;
import com.ryums.bookmark.service.TagService;
import com.ryums.bookmark.utils.UtilMethod;
import com.ryums.bookmark.utils.files.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
public class TagController {

    private TagService tagService;

    private StorageService storageService;

    private UtilMethod utilMethod;

    @RequestMapping("/tag/create")
    public ModelAndView createTagPage() {
        return new ModelAndView("/tag/tagCreate", utilMethod.setType("tag"));
    }

    @RequestMapping("/tag/create.do")
    public String createTag(TagDTO tagDTO, MultipartHttpServletRequest request) {
        String fileName = tagService.createTag(tagDTO);
        MultipartFile file = request.getFile("tagImg");
        storageService.store(file, fileName);
        return "redirect:/mark/create";
    }
}
