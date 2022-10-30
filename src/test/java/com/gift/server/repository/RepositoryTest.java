package com.gift.server.repository;

import com.gift.server.gift.Answer;
import com.gift.server.gift.Gift;
import com.gift.server.gift.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @Test
    public void test() {
        Repository repository = new Repository();

        Gift gift = Gift.builder().question(new Question(123433, "title", "qbod4ey"))
                .answerList(new ArrayList<>())
                .build();
        gift.answerList.add(new Answer("anbody"));
        gift.answerList.add(new Answer("anbody222"));
        gift.answerList.add(new Answer("anbody222333"));

        repository.storeStackoverflowGift(gift);

        Gift oneStackoverflowQuestion = repository.getOneStackoverflowGift();
        System.out.println("oneStackoverflowQuestion = " + oneStackoverflowQuestion);


    }


    @Test
    public void regrexTest() {
        String str = "<p>Let's disregard the <code>MASK</code>, <code>MAX_INT</code> and <code>MIN_INT</code>";
        System.out.println("str = " + str);

        System.out.println("after = " +str.replaceAll("\'", "\'\'").replaceAll("\"", "\"\""))
        ;


    }

}