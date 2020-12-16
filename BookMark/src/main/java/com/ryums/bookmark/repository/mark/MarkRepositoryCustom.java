package com.ryums.bookmark.repository.mark;

import com.ryums.bookmark.entity.MarkEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
 * 커스텀 쿼리 메소드 정의 인터페이스
 * */
public interface MarkRepositoryCustom {
    List<MarkEntity> findMarkList(String tagName, String markTitle, String useYn, Pageable pageable);
    long getMarkCount(String tagName, String markTitle, String useYn);
}
