package com.ryums.bookmark.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "mark")
public class MarkEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 20)
    private String title;

    @Column(length = 40)
    private String tags;

    @Column(length = 100)
    private String url;

    @Builder
    public MarkEntity(Long idx, String title, String tags, String url) {
        this.idx = idx;
        this.title = title;
        this.tags = tags;
        this.url = url;
    }

}
