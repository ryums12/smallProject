package com.ryums.bookmark.dto;

import com.ryums.bookmark.domain.entity.MarkEntity;
import com.ryums.bookmark.domain.entity.TagEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MarkDTO {

    private Long markIdx;
    private String markTitle;
    private Long tagIdx;
    private String markUrl;
    private TagEntity tagEntity;

    public MarkEntity toEntity() {
        MarkEntity builder = MarkEntity.builder()
                .markIdx(markIdx)
                .markTitle(markTitle)
                .tagIdx(tagIdx)
                .markUrl(markUrl)
                .build();

        return builder;
    }
}
