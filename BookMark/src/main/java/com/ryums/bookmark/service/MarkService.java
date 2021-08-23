package com.ryums.bookmark.service;

import com.ryums.bookmark.entity.MarkEntity;
import com.ryums.bookmark.repository.mark.MarkRepository;
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

    private final MarkRepository markRepository;
    private final ModelMapper modelMapper;
    private final UtilMethod utilMethod;

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

        int page = Integer.parseInt(param.get("page").toString());
        int size = Integer.parseInt(param.get("size").toString());
        String tag = param.get("tag").toString();
        String title = param.get("title").toString();
        String useYn = param.get("useYn").toString();

        Pageable pageable = PageRequest.of(page, size, Sort.by("chgDt").descending());

        List<MarkEntity> markEntityList = markRepository.findMarkList(tag, title, useYn, pageable);
        List<MarkDTO> markList = modelMapper.map(markEntityList, new TypeToken<List<MarkDTO>>() {}.getType());

        for(int i = 0; i < markEntityList.size(); i++) {
            markList.get(i).setImgUrl(markEntityList.get(i).getTagEntity().getImgUrl());
        }

        long listSize = markRepository.getMarkCount(tag, title, useYn);

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
