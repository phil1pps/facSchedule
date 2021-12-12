package com.example.facSchedule.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static java.util.Arrays.asList;


@Configuration
@EnableScheduling
@EnableCaching
@EnableWebMvc
public class Config implements WebMvcConfigurer {

    @Bean
    public CacheManager cacheManager() {
        return new CustomCache(asList("AllSpeciality"));
    }

}
