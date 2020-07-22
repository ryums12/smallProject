package com.ryums.bookmark.dto;

import com.ryums.bookmark.domain.entity.MarkEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MarkDTO {

    private String markTitle;
    private Long tagIdx;
    private String markUrl;

    public MarkEntity toEntity() {
        MarkEntity builder = MarkEntity.builder()
                .markTitle(markTitle)
                .tagIdx(tagIdx)
                .markUrl(markUrl)
                .build();

        return builder;
    }
}
