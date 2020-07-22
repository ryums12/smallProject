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
    private Long markIdx;

    @Column(name = "mark_title")
    private String markTitle;

    @Column(name = "mark_url")
    private String markUrl;

    @Column(name = "tag_idx")
    private Long tagIdx;

    @ManyToOne
    @JoinColumn(name = "tag_idx", insertable = false, updatable = false)
    private TagEntity tagEntity;

    @Builder
    public MarkEntity(String markTitle, Long tagIdx, String markUrl) {
        this.markTitle = markTitle;
        this.tagIdx = tagIdx;
        this.markUrl = markUrl;
    }

}
