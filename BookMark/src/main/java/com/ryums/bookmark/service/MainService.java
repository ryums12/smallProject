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
    public List<MarkEntity> getMarkList(Map<String, Object> param) {
        int page = Integer.parseInt((String) param.get("page"));
        String tag = (String) param.get("tag");
        Pageable pageable = PageRequest.of(page, 8, Sort.by("idx").descending());

        return mainRepository.findAllByTagContaining(pageable, tag);
    }
}
