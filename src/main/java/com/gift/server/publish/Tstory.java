package com.gift.server.publish;

import com.gift.server.config.Config;
import com.gift.server.http.HttpRequest;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Tstory {

    @Autowired
    private Config config;

    String getCategoryNum(String categoryStr) {
        HttpRequest httpRequest = new HttpRequest();

        String url = "https://www.tistory.com/apis/category/list?access_token=" + config.getTstoryToken()
                +"&blogName=Cuire&output=json";

        JSONObject jsonByUrl = httpRequest.getJsonByUrl(url, HttpMethod.GET);
        JSONObject tistory = (JSONObject) jsonByUrl.get("tistory");
        JSONObject item = (JSONObject) tistory.get("item");
        JSONArray categories = (JSONArray) item.get("categories");
        for (Object category : categories) {
            JSONObject categoryJson = (JSONObject) category;
            if (categoryJson.get("name").equals(categoryStr)) {
                //System.out.println(categoryJson.get("id"));
                return (String)categoryJson.get("id");
            }
        }
        // 못찾으면 기타 카테고리
        return "719739";
    }
    boolean uploadPost(Post post) {
        String requestUrl = "https://www.tistory.com/apis/post/write?access_token=" +
                config.getTstoryToken() + "&blogName=Cuire&title=" +
                post.title + "&category=" +
                getCategoryNum(config.getTstoryCategory()) + "&content=" +
                post.content;

        HttpRequest httpRequest = new HttpRequest();
        JSONObject jsonByUrl = httpRequest.getJsonByUrl(requestUrl, HttpMethod.POST);

        System.out.println("jsonByUrl = " + jsonByUrl);
        return true;
    }
}
