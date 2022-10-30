package com.gift.server.stackoverflow;

import com.gift.server.config.StackoverflowConfig;
import com.gift.server.gift.Gift;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StackExchangeImplTest {

    @Autowired
    private StackoverflowConfig stackoverflowConfig;

    @Test
    public void getGiftTest() {
        StackExchange stackExchange = new StackExchangeImpl(stackoverflowConfig);

        //List<Gift> svelte = stackExchange.getGifts("Svelte");

        System.out.println("StackExchangeImplTest.getGiftTest");
    }

}