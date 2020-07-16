package com.ryums.bookmark.domain.repository;

import com.ryums.bookmark.domain.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository <TagEntity, Long> {
    
}
