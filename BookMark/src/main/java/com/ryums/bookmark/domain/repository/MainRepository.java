package com.ryums.bookmark.domain.repository;

import com.ryums.bookmark.domain.entity.MarkEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainRepository extends JpaRepository<MarkEntity, Long> {
    List<MarkEntity> findAllByTagContaining(Pageable pageable, String tag);
}
