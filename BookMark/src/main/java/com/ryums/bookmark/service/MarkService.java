package com.ryums.bookmark.service;

import com.ryums.bookmark.domain.entity.MarkEntity;
import com.ryums.bookmark.domain.repository.MarkRepository;
import com.ryums.bookmark.dto.MarkDTO;
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
    private TagService tagService;
    private ModelMapper modelMapper;

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

        List<MarkEntity> entityMarkList = markRepository.findMarkList(tag, "Y", pageable);
        List<MarkDTO> markList = modelMapper
                .map(entityMarkList, new TypeToken<List<MarkDTO>>() {}.getType());

        for(int i = 0; i < entityMarkList.size(); i++) {
            markList.get(i).setTagName(entityMarkList.get(i).getTagEntity().getTagName());
            markList.get(i).setImgUrl(entityMarkList.get(i).getTagEntity().getImgUrl());
        }

        int listSize = markRepository.getMarkCount(tag);

        dataMap.put("markList", markList);
        dataMap.put("size", listSize);

        return dataMap;
    }

    @Transactional
    public ModelMap getMarkDetail(Long markIdx) {

        ModelMap modelMap = tagService.getTagList();
        MarkEntity markEntity = markRepository.findAllByMarkIdx(markIdx);
        MarkDTO markDTO = modelMapper.map(markEntity, MarkDTO.class);
        modelMap.put("mark", markDTO);

        return modelMap;
    }
}
