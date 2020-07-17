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

    @Column(length = 40)
    private String markTitle;

    @Column()
    private Long tagIdx;
//
//    @Column(length = 30)
//    private String tagName;

    @Column(length = 100)
    private String markUrl;

    @Builder
    public MarkEntity(Long markIdx, String markTitle, Long tagIdx, String tagName, String markUrl) {
        this.markIdx = markIdx;
        this.markTitle = markTitle;
        this.tagIdx = tagIdx;
//        this.tagName = tagName;
        this.markUrl = markUrl;
    }

}
