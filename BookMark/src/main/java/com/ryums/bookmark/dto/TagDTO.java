package com.ryums.bookmark.dto;

import com.ryums.bookmark.domain.entity.TagEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TagDTO {

    private Long tagIdx;
    private String tagName;
    private String imgUrl;

    public TagEntity toEntity() {
        TagEntity builder = TagEntity.builder()
                .tagIdx(tagIdx)
                .tagName(tagName)
                .imgUrl(imgUrl)
                .build();

        return builder;
    }
}
