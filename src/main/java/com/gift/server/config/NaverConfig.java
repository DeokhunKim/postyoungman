package com.gift.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("config.naver")
@Data
public class NaverConfig {
    private String SearchUrl;
    private String clientStr;
    private String clientIdCode;
    private String secretStr;
    private String secretCode;

}
