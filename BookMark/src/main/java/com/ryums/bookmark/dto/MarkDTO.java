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
    private String tag;
    private String url;

    public MarkEntity toEntity() {
        MarkEntity builder = MarkEntity.builder()
                .idx(idx)
                .title(title)
                .tag(tag)
                .url(url)
                .build();

        return builder;
    }
}
