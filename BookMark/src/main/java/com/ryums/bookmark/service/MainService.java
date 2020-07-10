package com.ryums.bookmark.service;

import com.ryums.bookmark.domain.entity.MarkEntity;
import com.ryums.bookmark.domain.repository.MainRepository;
import com.ryums.bookmark.dto.MarkDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MainService {

    private MainRepository mainRepository;

    @Transactional
    public void createMark(MarkDTO markDTO) {
        mainRepository.save(markDTO.toEntity());
    }

    @Transactional
    public Map<String, Object> getMarkListData(Map<String, Object> param) {
        Map<String, Object> dataMap = new HashMap<>();

        int page = Integer.parseInt((String) param.get("page"));
        String tag = (String) param.get("tag");
        Pageable pageable = PageRequest.of(page, 8, Sort.by("idx").descending());

        List<MarkEntity> markList = mainRepository.findAllByTagContaining(pageable, tag);
        int listSize = mainRepository.countAllByTagContaining(tag);

        dataMap.put("markList", markList);
        dataMap.put("size", listSize);

        return dataMap;
    }
}
