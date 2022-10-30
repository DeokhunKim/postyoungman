package com.gift.server.publish.scheduler;

import com.gift.server.config.Config;
import com.gift.server.gift.Gift;
import com.gift.server.publish.PublishManager;
import com.gift.server.stackoverflow.StackExchange;
import com.gift.server.stackoverflow.StackExchangeImpl;
import com.gift.server.translate.TranslateBundle;
import com.gift.server.translate.adaptor.GiftAdaptor;
import com.gift.server.translate.naver.NaverTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
public class Scheduler {
    @Autowired
    private PublishManager publishManager;
    @Autowired
    private StackExchangeImpl stackExchange;
    @Autowired
    private Config config;
    @Autowired
    private NaverTranslate naverTranslate;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 12 /* 12시간 */ )
    //@Scheduled(fixedRate = 1000 )
    public void random() {
        //log.info("trigger random schedule");
        int i = ThreadLocalRandom.current().nextInt(1000 * 60 * 60 * 3 /* 3시간 */);
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void doSomething() {
        List<String> findKeywords = config.getFindKeywords();
        String keyword = findKeywords.get(ThreadLocalRandom.current().nextInt(findKeywords.size()));
        System.out.println("search keyword: " + keyword);
        List<Gift> gifts = stackExchange.getGifts(keyword);
        System.out.println("get gift " + gifts.size());
        List<Gift> tanslateGifts = new ArrayList<>();
        for (Gift gift : gifts) {
            System.out.println("translate...");
            GiftAdaptor giftAdaptor = new GiftAdaptor(gift);
            TranslateBundle translateBundle = naverTranslate.eng2kor_exceptHtml(giftAdaptor);
            Gift translateGift = new GiftAdaptor(gift.question.questionNum, translateBundle).getGift();
            tanslateGifts.add(translateGift);
        }

        publishManager.storeGifts(tanslateGifts);
        publishManager.postOneGift();
    }
}
