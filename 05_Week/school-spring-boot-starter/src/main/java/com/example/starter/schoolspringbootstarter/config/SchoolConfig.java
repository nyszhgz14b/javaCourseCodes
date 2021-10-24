package com.example.starter.schoolspringbootstarter.config;

import com.example.starter.schoolspringbootstarter.properties.SchoolProperties;
import com.example.starter.schoolspringbootstarter.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SchoolProperties.class)
@ConditionalOnProperty(prefix = "school",name = "isopen",havingValue = "true")
public class SchoolConfig {
    @Autowired
    private SchoolProperties schoolProperties;
    public SchoolService schoolService(){
        return new SchoolService(schoolProperties.getName(),schoolProperties.getCity(),schoolProperties.getTowns());
    }
}
