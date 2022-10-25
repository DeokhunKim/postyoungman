package com.gift.server.translate;

import com.gift.server.gift.Answer;
import com.gift.server.gift.Gift;
import com.gift.server.gift.Question;

import java.util.ArrayList;
import java.util.List;

public class GiftAdaptor extends TranslateBundle{
    private int questionNum;

    public GiftAdaptor(Gift gift) {
        this.questionNum = gift.question.questionNum;
        super.add(gift.question.title);
        super.add(gift.question.body);
        for (Answer answer : gift.answerList) {
            super.add(answer.body);
        }
    }



}
