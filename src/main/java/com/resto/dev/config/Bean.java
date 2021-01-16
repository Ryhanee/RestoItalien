package com.resto.dev.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
@Configuration
public class Bean {

        @org.springframework.context.annotation.Bean
        public ModelMapper getModelMapper() {
            return new ModelMapper();
        }
    }

