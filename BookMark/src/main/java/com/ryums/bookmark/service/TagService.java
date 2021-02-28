package com.ryums.bookmark.service;

import com.querydsl.core.Tuple;
import com.ryums.bookmark.entity.MarkEntity;
import com.ryums.bookmark.entity.TagEntity;
import com.ryums.bookmark.repository.tag.TagRepository;
import com.ryums.bookmark.dto.TagDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository tagRepository;
    private ModelMapper modelMapper;

    public Map<String, Object> setTagDTO(TagDTO tagDTO, String fileName) {

        Map<String, Object> result = new HashMap<>();

        int pos = fileName.lastIndexOf(".");
        String ext = fileName.substring(pos + 1);
        String imgName = "tag_" + tagDTO.getTagName() + "." + ext;
        String imgUrl = "/files/" + imgName;
        tagDTO.setImgUrl(imgUrl);

        result.put("tagDto", tagDTO);
        result.put("imgName", imgName);

        return result;
    }

    @Transactional
    public void createTag(TagDTO tagDTO) {
        tagRepository.save(tagDTO.toEntity());
    }

    @Transactional
    public Map<String, Object> getTagList(Map<String, Object> param) {

        Map<String, Object> dataMap = new HashMap<>();
        List tagList = new ArrayList<>();

        int page = Integer.parseInt((String) param.get("page"));
        int size = Integer.parseInt((String) param.get("size"));
        String tagName = (String) param.get("tag");

        Pageable pageable = PageRequest.of(page, size, Sort.by("tagIdx").descending());

        if (param.get("isContainCount") != null) {
            tagList = tagRepository.getTagListContainCount(pageable);
        } else {
            tagList = tagRepository.getTagList(tagName, pageable);;
        }
        long listSize = tagRepository.getTotalTagCount(tagName);

        dataMap.put("tagList", tagList);
        dataMap.put("size", listSize);

        return dataMap;
    }

    @Transactional
    public ModelMap getTagDetail(Long markIdx) {

        ModelMap modelMap = new ModelMap();
        TagEntity tagEntity = tagRepository.findAllByTagIdx(markIdx);
        TagDTO tagDTO = modelMapper.map(tagEntity, TagDTO.class);

        modelMap.put("tag", tagDTO);

        return modelMap;
    }
}
