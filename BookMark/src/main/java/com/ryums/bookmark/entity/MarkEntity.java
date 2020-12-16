package com.ryums.bookmark.entity;

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

    @Column(name ="use_yn")
    private String useYn;

    @ManyToOne(targetEntity = TagEntity.class)
    @JoinColumn(name = "tag_idx", insertable = false, updatable = false)
    private TagEntity tagEntity;

    @Builder
    public MarkEntity(Long markIdx, String markTitle,
                      Long tagIdx, String markUrl, String useYn) {
        this.markIdx = markIdx;
        this.markTitle = markTitle;
        this.tagIdx = tagIdx;
        this.markUrl = markUrl;
        this.useYn = useYn;
    }

    @PrePersist
    public void prePersist() {
        this.useYn = this.useYn == null ? "Y" : this.useYn;
    }
}
