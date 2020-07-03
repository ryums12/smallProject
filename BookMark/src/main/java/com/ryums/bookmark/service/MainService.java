package com.ryums.bookmark.service;

import com.ryums.bookmark.domain.entity.TestEntity;
import com.ryums.bookmark.domain.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    MainRepository mainRepository;

    public List<TestEntity> test() {
        return mainRepository.findAll();
    }
}
