package com.ryums.bookmark.dto;

import com.ryums.bookmark.domain.entity.TestEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TestDTO {

    private long idx;
    private String content;

    public TestEntity toEntity() {
        TestEntity build = TestEntity.builder()
                .idx(idx)
                .content(content)
                .build();

        return build;
    }

    @Builder
    public TestDTO(long idx, String content) {
        this.idx = idx;
        this.content = content;
    }
}
