package com.gift.server.gift;

import lombok.Data;

@Data
public class Question {
    public int questionNum;
    public String title;
    public String body;

    public Question(int questionNum, String title, String body) {
        this.questionNum = questionNum;
        this.title = title;
        this.body = body;
    }

    public Question() {
    }
}
