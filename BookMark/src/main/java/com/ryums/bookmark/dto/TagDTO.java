package com.ryums.bookmark.dto;

import com.ryums.bookmark.entity.TagEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

    private Long tagIdx;
    @NotEmpty(message = "태그의 제목을 입력해 주십시오.")
    private String tagName;
    private String imgUrl;

    private String imgName;
    private Long tagCount;

    public TagEntity toEntity() {
        TagEntity builder = TagEntity.builder()
                .tagIdx(tagIdx)
                .tagName(tagName)
                .imgUrl(imgUrl)
                .build();

        return builder;
    }
}
