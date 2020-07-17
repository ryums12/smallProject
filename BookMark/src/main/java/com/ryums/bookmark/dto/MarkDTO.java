package com.ryums.bookmark.dto;

import com.ryums.bookmark.domain.entity.MarkEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MarkDTO {

    private Long markIdx;
    private String markTitle;
    private Long tagIdx;
//    private String tagName;
    private String markUrl;

    public MarkEntity toEntity() {
        MarkEntity builder = MarkEntity.builder()
                .markIdx(markIdx)
                .markTitle(markTitle)
                .tagIdx(tagIdx)
//                .tagName(tagName)
                .markUrl(markUrl)
                .build();

        return builder;
    }
}
