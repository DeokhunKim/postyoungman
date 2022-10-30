package com.gift.server.stackoverflow;

import com.gift.server.gift.Answer;
import com.gift.server.gift.Gift;
import com.gift.server.gift.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StackExchange {
    Gift getGift(String keyword);
    List<Gift> getGifts(String keyword);

}
