package com.ryums.bookmark.service;

import com.ryums.bookmark.domain.entity.TagEntity;
import com.ryums.bookmark.domain.repository.TagRepository;
import com.ryums.bookmark.dto.TagDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public List<TagDTO> getTagList() {

        List<TagEntity> entityTagList = tagRepository.findAll();
        List<TagDTO> tagList = modelMapper
                .map(entityTagList, new TypeToken<List<TagDTO>>() {}.getType());

        return tagList;
    };
}
