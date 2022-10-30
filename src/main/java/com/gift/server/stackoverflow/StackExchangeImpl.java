package com.gift.server.stackoverflow;

import com.gift.server.config.StackoverflowConfig;
import com.gift.server.gift.Answer;
import com.gift.server.gift.Gift;
import com.gift.server.gift.Question;
import com.gift.server.http.HttpRequest;
import lombok.AllArgsConstructor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@AllArgsConstructor
public class StackExchangeImpl implements StackExchange{

    private final StackoverflowConfig stackoverflowConfig;
    private final HttpRequest httpRequest = new HttpRequest();

    @Override
    public Gift getGift(String keyword) {
        return getGiftsByKeyword(keyword).get(0);
    }

    public List<Gift> getGifts(String keyword) {
        return getGiftsByKeyword(keyword);
    }

    private List<Gift> getGiftsByKeyword(String keyword) {
        List<Gift> gifts = new ArrayList<>();
        String requestUrl = stackoverflowConfig.getSearchKeywordUrl() + "&title=" + keyword;
        JSONObject resultJson = httpRequest.getJsonByUrl(requestUrl, HttpMethod.GET);
        if (resultJson == null) {
            return gifts;
        }

        JSONArray items = (JSONArray)resultJson.get("items");
        for (Object itemObject : items) {
            JSONObject item = (JSONObject)itemObject;
            String title = (String)item.get("title");
            String body = (String)item.get("body");
            Long question_id = (Long)item.get("question_id");
            Question question = new Question(question_id.intValue(), title, body);
            List<Answer> answerList = new ArrayList<>();

            JSONArray answers = (JSONArray)item.get("answers");
            for (Object answerObject : answers) {
                JSONObject answerJson = (JSONObject)answerObject;
                //String answerId = (String) answerJson.get("answer_id");
                //String answerTitle = (String) answerJson.get("title");
                String answerBody = (String) answerJson.get("body");
                Answer answer = new Answer(answerBody);
                answerList.add(answer);
            }

            Gift gift = Gift.builder()
                    .question(question)
                    .answerList(answerList)
                    .build();
            gifts.add(gift);
        }

        return gifts;
    }

}
