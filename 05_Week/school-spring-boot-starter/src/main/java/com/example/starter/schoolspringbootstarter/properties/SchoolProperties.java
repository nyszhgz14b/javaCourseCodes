package com.example.starter.schoolspringbootstarter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "school")
@Data
public class SchoolProperties {
    //校名
    private String name;
    //城市
    private String city;
    //乡镇
    private String towns;
}
