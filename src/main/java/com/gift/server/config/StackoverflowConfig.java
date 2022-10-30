package com.gift.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("config.stackoverflow")
@Data
public class StackoverflowConfig {
    private String searchKeywordUrl;
}
