package com.ryums.bookmark.service;

import com.ryums.bookmark.domain.repository.MainRepository;
import com.ryums.bookmark.dto.MarkDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class MainService {

    private MainRepository mainRepository;

    @Transactional
    public void createMark(MarkDTO markDTO) {
        mainRepository.save(markDTO.toEntity());
    }
}
