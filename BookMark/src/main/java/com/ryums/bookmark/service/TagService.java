package com.ryums.bookmark.service;

import com.ryums.bookmark.domain.repository.TagRepository;
import com.ryums.bookmark.dto.TagDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository tagRepository;

    @Transactional
    public String createTag(TagDTO tagDTO) {
        String imgName = "tag_" + tagDTO.getTagName();
        String imgUrl = "/files/" + imgName;
        tagDTO.setImgUrl(imgUrl);
        tagRepository.save(tagDTO.toEntity());

        return imgName;
    }
}
