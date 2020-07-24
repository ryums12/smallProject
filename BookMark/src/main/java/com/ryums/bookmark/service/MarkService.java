package com.ryums.bookmark.service;

import com.ryums.bookmark.domain.entity.MarkEntity;
import com.ryums.bookmark.domain.repository.MarkRepository;
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
    private TagService tagService;

    @Transactional
    public void createMark(MarkDTO markDTO) {
        markRepository.save(markDTO.toEntity());
    }

    @Transactional
    public Map<String, Object> getMarkListData(Map<String, Object> param) {

        Map<String, Object> dataMap = new HashMap<>();
        int page = Integer.parseInt((String) param.get("page"));
        String tag = (String) param.get("tag");
        Pageable pageable = PageRequest.of(page, 8, Sort.by("markIdx").descending());

        List<MarkEntity> markList = markRepository.findMarkList(tag, pageable);
        int listSize = markRepository.getMarkCount(tag);

        dataMap.put("markList", markList);
        dataMap.put("size", listSize);

        return dataMap;
    }

    @Transactional
    public ModelMap getMarkDetail(Long markIdx) {

        ModelMap modelMap = tagService.getTagList();
        modelMap.put("mark", markRepository.findAllByMarkIdx(markIdx));

        return modelMap;
    }
}
