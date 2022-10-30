package com.gift.server.translate.adaptor;

import com.gift.server.config.Config;
import com.gift.server.config.StackoverflowConfig;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GiftAdaptorTest {

    @Autowired
    private Config config;
    @Autowired
    private StackoverflowConfig stackoverflowConfig;

    @Test
    public void test() {
        System.out.println("GiftAdaptorTest.test");
        System.out.println("config.getFindKeywords() = " + config.getFindKeywords());
        System.out.println("config.getInterval() = " + config.getInterval());

        System.out.println("stackoverflowConfig = " + stackoverflowConfig.getSearchKeywordUrl());
    }

}