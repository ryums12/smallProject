package com.ryums.bookmark.dto;

import com.ryums.bookmark.entity.MarkEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MarkDTO {

    private Long markIdx;
    @NotEmpty(message = "마크의 제목을 입력해 주십시오.")
    private String markTitle;
    @NotNull(message = "태그를 선택해 주십시오.")
    private Long tagIdx;
    @NotEmpty(message = "마크의 링크를 입력해 주십시오.")
    private String markUrl;
    private String useYn;

    private String imgUrl;
    private String TagName;

    public MarkEntity toEntity() {
        MarkEntity builder = MarkEntity.builder()
                .markIdx(markIdx)
                .markTitle(markTitle)
                .tagIdx(tagIdx)
                .markUrl(markUrl)
                .useYn(useYn)
                .build();

        return builder;
    }
}
