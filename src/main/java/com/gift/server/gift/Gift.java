package com.gift.server.gift;

import lombok.Builder;

import java.util.List;

@Builder
public class Gift {
    public Question question;
    public List<Answer> answerList;

}
