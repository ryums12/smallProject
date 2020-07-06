package com.ryums.bookmark.dto;

import com.ryums.bookmark.domain.entity.MarkEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MarkDTO {

    private Long idx;
    private String title;
    private String tags;
    private String url;

    public MarkEntity toEntity() {
        MarkEntity builder = MarkEntity.builder()
                .idx(idx)
                .title(title)
                .tags(tags)
                .url(url)
                .build();

        return builder;
    }
}
