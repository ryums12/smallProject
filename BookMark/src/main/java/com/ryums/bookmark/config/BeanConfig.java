package com.ryums.bookmark.config;

import com.ryums.bookmark.utils.UtilMethod;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UtilMethod utilMethod() { return new UtilMethod(); }
}
