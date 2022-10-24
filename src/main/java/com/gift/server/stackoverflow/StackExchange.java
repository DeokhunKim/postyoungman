package com.gift.server.stackoverflow;

import com.gift.server.gift.Answer;
import com.gift.server.gift.Gift;
import com.gift.server.gift.Question;

import java.util.List;

public interface StackExchange {
    Gift getGift(int pageNum);

    List<Integer> getQuestsByKeyword(String keyword, int limit);

    Question getQuestionByQeustionNum(int questionNum);

    List<Answer> getAnswerByQuestionNum(int questionNum);

}
