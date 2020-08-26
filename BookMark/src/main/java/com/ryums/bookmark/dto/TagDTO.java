package com.ryums.bookmark.dto;

import com.ryums.bookmark.domain.entity.TagEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TagDTO {

    private Long tagIdx;
    @NotEmpty(message = "태그의 제목을 입력해 주십시오.")
    private String tagName;
    private String imgUrl;
    private String imgName;

    public TagEntity toEntity() {
        TagEntity builder = TagEntity.builder()
                .tagIdx(tagIdx)
                .tagName(tagName)
                .imgUrl(imgUrl)
                .build();

        return builder;
    }
}
