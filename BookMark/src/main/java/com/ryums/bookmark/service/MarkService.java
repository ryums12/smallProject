package com.ryums.bookmark.service;

import com.ryums.bookmark.domain.entity.MarkEntity;
import com.ryums.bookmark.domain.entity.TagEntity;
import com.ryums.bookmark.domain.repository.MarkRepository;
import com.ryums.bookmark.domain.repository.TagRepository;
import com.ryums.bookmark.dto.MarkDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MarkService {

    private MarkRepository markRepository;
    private TagRepository tagRepository;

    @Transactional
    public ModelMap getTagList() {
        ModelMap modelMap = new ModelMap();

        List<TagEntity> tagList = tagRepository.findAll();
        modelMap.put("tagList", tagList);

        return modelMap;
    };

    @Transactional
    public void createMark(MarkDTO markDTO) {
        markRepository.save(markDTO.toEntity());
    }

    @Transactional
    public Map<String, Object> getMarkListData(Map<String, Object> param) {
        Map<String, Object> dataMap = new HashMap<>();

//        int page = Integer.parseInt((String) param.get("page"));
//        String tag = (String) param.get("tag");
//        Pageable pageable = PageRequest.of(page, 8, Sort.by("markIdx").descending());

//        List<MarkEntity> markList = markRepository.findAllByTagNameContaining(pageable, tag);
//        int listSize = markRepository.countAllByTagNameContaining(tag);
//
//        dataMap.put("markList", markList);
//        dataMap.put("size", listSize);

        return dataMap;
    }
}
