package com.ryums.bookmark.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "tag_idx", insertable = false, updatable = false)
    private TagEntity tagEntity;

    @Builder
    public MarkEntity(Long markIdx, String markTitle, Long tagIdx, String markUrl) {
    }

}
