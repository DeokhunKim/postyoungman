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
        System.out.println("Tstory.getCategoryNum - start");
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
                System.out.println("Tstory.getCategoryNum - end");
                return (String)categoryJson.get("id");
            }
        }
        // 못찾으면 기타 카테고리
        System.out.println("Tstory.getCategoryNum - end");
        return "719739";
    }
    boolean uploadPost(Post post) {
        System.out.println("Tstory.uploadPost");

        /*String requestUrl = "https://www.tistory.com/apis/post/write?access_token=" +
                config.getTstoryToken() + "&blogName=Cuire&visibility=3&title=" +
                post.title.replaceAll("&", "%26") + "&category=" +
                getCategoryNum(config.getTstoryCategory()) + "&content=" +
                post.content.replaceAll("&", "%26");*/

        String requestUrl = "https://www.tistory.com/apis/post/write?access_token=" +
                config.getTstoryToken() + "&blogName=Cuire&visibility=3&category=" +
                getCategoryNum(config.getTstoryCategory());

        String body = "{\"title\":\"" + post.title.replaceAll("\\\\", "\\\\\\\\").replaceAll("\n", "\\\\n").replaceAll("\"", "\\\\\"")
                + "\", \"content\":\"" + post.content.replaceAll("\\\\", "\\\\\\\\").replaceAll("\n", "\\\\n").replaceAll("\"", "\\\\\"") + "\"}" ;

        System.out.println("requestUrl = " + requestUrl);
        HttpRequest httpRequest = new HttpRequest();
        boolean request = httpRequest.request(requestUrl, HttpMethod.POST, body);

        System.out.println("jsonByUrl = " + request);
        return true;
    }
}
