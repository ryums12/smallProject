package com.ryums.bookmark.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TagIncludeCountDTO {

    private Long tagIdx;
    private String tagName;
    private String imgUrl;
    private Long tagCount;
}
