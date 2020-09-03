package com.ryums.bookmark.domain.repository;

import com.ryums.bookmark.domain.entity.TagEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository <TagEntity, Long> {

    List<TagEntity> findByTagNameContaining(String tagName, Pageable pageable);

    int countAllByTagNameContaining(String tagName);

    TagEntity findAllByTagIdx(Long tagIdx);

}
