package com.ryums.bookmark.service;

import com.ryums.bookmark.domain.entity.MarkEntity;
import com.ryums.bookmark.domain.repository.MarkRepository;
import com.ryums.bookmark.dto.MarkDTO;
import com.ryums.bookmark.utils.UtilMethod;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    private ModelMapper modelMapper;
    private UtilMethod utilMethod;

    @Transactional
    public ModelMap setMarkCreatePage() {
        return utilMethod.setType("mark");
    }

    @Transactional
    public void saveMark(MarkDTO markDTO) {
        markRepository.save(markDTO.toEntity());
    }

    @Transactional
    public Map<String, Object> getMarkListData(Map<String, Object> param) {

        Map<String, Object> dataMap = new HashMap<>();

        int page = Integer.parseInt((String) param.get("page"));
        int size = Integer.parseInt((String) param.get("size"));
        String tag = (String) param.get("tag");
        String useYn = (String) param.get("useYn");

        Pageable pageable = PageRequest.of(page, size, Sort.by("chgDt").descending());

        List<MarkEntity> markEntityList = markRepository.findMarkList(tag, useYn, pageable);
        List<MarkDTO> markList = modelMapper
                .map(markEntityList, new TypeToken<List<MarkDTO>>() {}.getType());

        for(int i = 0; i < markEntityList.size(); i++) {
            markList.get(i).setTagName(markEntityList.get(i).getTagEntity().getTagName());
            markList.get(i).setImgUrl(markEntityList.get(i).getTagEntity().getImgUrl());
        }

        int listSize = markRepository.getMarkCount(tag, useYn);

        dataMap.put("markList", markList);
        dataMap.put("size", listSize);

        return dataMap;
    }

    @Transactional
    public ModelMap getMarkDetail(Long markIdx) {

        ModelMap modelMap = new ModelMap();
        MarkEntity markEntity = markRepository.findAllByMarkIdx(markIdx);
        MarkDTO markDTO = modelMapper.map(markEntity, MarkDTO.class);

        modelMap.put("mark", markDTO);

        return modelMap;
    }
}
