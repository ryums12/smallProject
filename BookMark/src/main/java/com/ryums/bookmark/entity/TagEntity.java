package com.ryums.bookmark.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tag")
public class TagEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagIdx;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "img_url")
    private String imgUrl;

    @Builder
    public TagEntity(Long tagIdx, String tagName, String imgUrl) {
        this.tagIdx = tagIdx;
        this.tagName = tagName;
        this.imgUrl = imgUrl;
    }

    @PrePersist
    public void prePersist() {
        this.imgUrl = this.imgUrl == null ? "/images/icons/post.png" : this.imgUrl;
    }

}
