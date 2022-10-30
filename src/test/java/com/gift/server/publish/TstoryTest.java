package com.gift.server.publish;

import com.gift.server.config.Config;
import com.gift.server.http.HttpRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TstoryTest {


    @Autowired
    private Config config;

    @Test
    void getCategoryNumTest() {

        Tstory t = new Tstory(config);
        String code = t.getCategoryNum("코딩");
        System.out.println("code = " + code);
    }

    @Test
    void uploadPostTest() {
        Post post = new Post();
        post.category = "개발하자";
        post.title = "테스트 업로드 입니다";
        post.content = "<h1>테스트 업로드의 내용입니다</h1> <p>잘 나오나?";

        Tstory t = new Tstory(config);
        t.uploadPost(post);


        System.out.println("TstoryTest.uploadPostTest");
    }

}