package com.gift.server.publish;

import com.gift.server.gift.Answer;
import com.gift.server.gift.Gift;
import com.gift.server.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublishManager {
    @Autowired
    private Repository repository;
    @Autowired
    private Tstory tstory;

    public void storeGifts(List<Gift> gifts) {
        for (Gift gift : gifts) {
            repository.storeStackoverflowGift(gift);
        }
    }
    public void postOneGift() {
        System.out.println("post gift");
        Gift gift = repository.getOneStackoverflowGift();
        if (gift == null) {
            System.out.println("error get gift");
        }

        Post post = new Post();
        post.title = gift.question.title;
        post.content = gift.question.body;
        for (Answer answer : gift.answerList) {
            post.content += "<br/><hr><br/>";
            post.content += answer.body;
        }

        tstory.uploadPost(post);

    }
}
