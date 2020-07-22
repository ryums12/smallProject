package com.ryums.bookmark.domain.repository;

import com.ryums.bookmark.domain.entity.MarkEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarkRepository extends JpaRepository<MarkEntity, Long> {

    @Query(value = "select mark from MarkEntity mark where mark.tagEntity.tagName like %?1%")
    List<MarkEntity> findMarkList(String tagName, Pageable pageable);

    @Query(value = "select count(mark) from MarkEntity mark where mark.tagEntity.tagName like %?1%")
    int getMarkCount(String tagName);
}
