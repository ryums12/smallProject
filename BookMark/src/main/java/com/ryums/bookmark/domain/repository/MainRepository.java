package com.ryums.bookmark.domain.repository;

import com.ryums.bookmark.domain.entity.MarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainRepository extends JpaRepository<MarkEntity, Long> {
}
