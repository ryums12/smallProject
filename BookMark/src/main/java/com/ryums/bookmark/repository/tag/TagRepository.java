package com.ryums.bookmark.repository.tag;

import com.ryums.bookmark.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository <TagEntity, Long>, TagRepositoryCustom {
    TagEntity findAllByTagIdx(Long tagIdx);
}
