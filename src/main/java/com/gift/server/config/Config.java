package com.gift.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties("config")
@Data
public class Config {
    private List<String> findKeywords = new ArrayList<>();
    private String tstoryToken;
    private String tstoryCategory;
    private String interval;
}
