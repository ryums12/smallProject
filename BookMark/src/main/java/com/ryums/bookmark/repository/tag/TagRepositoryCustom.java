package com.ryums.bookmark.repository.tag;

import com.ryums.bookmark.dto.TagDTO;
import com.ryums.bookmark.dto.TagIncludeCountDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
 * 커스텀 쿼리 메소드 정의 인터페이스
 * */
public interface TagRepositoryCustom {
    List<TagDTO> getTagList(String tagName, Pageable pageable);
    List<TagIncludeCountDTO> getTagListContainCount(Pageable pageable);
    long getTotalTagCount(String tagName);
}
