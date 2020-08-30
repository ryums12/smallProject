package com.ryums.bookmark.domain.repository;

import com.ryums.bookmark.domain.entity.MarkEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarkRepository extends JpaRepository<MarkEntity, Long> {

    @Query(value = "select mark from MarkEntity mark "
                 + "where mark.tagEntity.tagName like %?1%"
                 + "and mark.markTitle like %?2%"
                 + "and mark.useYn = ?3")
    List<MarkEntity> findMarkList(String tagName, String markTitle, String useYn, Pageable pageable);

    @Query(value = "select count(mark) from MarkEntity mark "
                 + "where mark.tagEntity.tagName like %?1%"
                 + "and mark.markTitle like %?2%"
                 + "and mark.useYn = ?3")
    int getMarkCount(String tagName, String markTitle, String useYn);

    MarkEntity findAllByMarkIdx(Long markIdx);
}
