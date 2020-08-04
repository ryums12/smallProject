package com.ryums.bookmark.service;

import com.ryums.bookmark.domain.entity.TagEntity;
import com.ryums.bookmark.domain.repository.TagRepository;
import com.ryums.bookmark.dto.TagDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
public class TagService {

    private TagRepository tagRepository;

    private ModelMapper modelMapper;

    @Transactional
    public String createTag(TagDTO tagDTO) {

        String imgName = "tag_" + tagDTO.getTagName();
        String imgUrl = "/files/" + imgName;
        tagDTO.setImgUrl(imgUrl);
        tagRepository.save(tagDTO.toEntity());

        return imgName;
    }

    @Transactional
    public Map<String, Object> getTagList(Map<String, Object> param) {

        Map<String, Object> dataMap = new HashMap<>();

        int page = Integer.parseInt((String) param.get("page"));
        int size = Integer.parseInt((String) param.get("size"));
        String tag = (String) param.get("tag");

        Pageable pageable = PageRequest.of(page, size, Sort.by("tagIdx").descending());

        List<TagEntity> entityTagList = tagRepository.findByTagNameContaining(tag, pageable);
        List<TagDTO> tagList = modelMapper
                .map(entityTagList, new TypeToken<List<TagDTO>>() {}.getType());

        int listSize = tagRepository.countAllByTagNameContaining(tag);

        dataMap.put("tagList", tagList);
        dataMap.put("size", listSize);

        return dataMap;

//        List<TagEntity> entityTagList = tagRepository.findAll();
//        List<TagDTO> tagList = modelMapper
//                .map(entityTagList, new TypeToken<List<TagDTO>>() {}.getType());
//
//
//
//        return tagList;
    };
}
