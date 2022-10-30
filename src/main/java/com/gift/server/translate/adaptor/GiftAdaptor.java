package com.gift.server.translate.adaptor;

import com.gift.server.gift.Answer;
import com.gift.server.gift.Gift;
import com.gift.server.gift.Gift.GiftBuilder;
import com.gift.server.gift.Question;
import com.gift.server.translate.TranslateBundle;


import java.util.ArrayList;
public class GiftAdaptor extends TranslateBundle {
    private final int questionNum;

    public GiftAdaptor(int questionNum, TranslateBundle translateBundle) {
        super.setStrList(translateBundle.getStrList());
        this.questionNum = questionNum;
    }

    public GiftAdaptor(Gift gift) {
        this.questionNum = gift.question.questionNum;
        super.add(gift.question.title);
        super.add(gift.question.body);
        for (Answer answer : gift.answerList) {
            super.add(answer.body);
        }
    }

    public Gift getGift() {
        Question question = new Question(this.questionNum, (String) super.removeFirst(),(String) super.removeFirst());
        GiftBuilder builder = Gift.builder();
        builder.question(question)
                .answerList(new ArrayList<>());
        Gift gift = builder.build();
        for (String answer : super.getStrList()) {
            gift.answerList.add(new Answer(answer));
        }

        return gift;
    }

}
