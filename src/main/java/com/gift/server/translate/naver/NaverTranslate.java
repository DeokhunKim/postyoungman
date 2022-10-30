package com.gift.server.translate.naver;

import com.gift.server.config.NaverConfig;
import com.gift.server.http.HttpRequest;
import com.gift.server.translate.TranslateBundle;
import com.gift.server.translate.Translator;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class NaverTranslate implements Translator {
    private final NaverConfig naverConfig;

    @Override
    public TranslateBundle eng2kor(TranslateBundle engStrList) {
        return null;
    }

    @Override
    public TranslateBundle eng2kor_exceptHtml(TranslateBundle engStrList) {
        TranslateBundle result = new TranslateBundle();
        while (!engStrList.isEmpty()) {
            String s = engStrList.removeFirst();
            Document parse = Jsoup.parse(s);
            Elements select = parse.select("*");

            for (Element element : select) {
                if (!element.ownText().isEmpty()) {
                    if (element.tagName().equals("code")) {
                        continue;
                    }
                    String translate = translate(element.ownText());
                    if (translate != null) {
                        element.text(translate);
                    }
                }
            }
            result.add(parse.body().html());
        }

        return result;
    }


    public String translate(String input) {
        HttpRequest httpRequest = new HttpRequest();
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(naverConfig.getClientStr(), naverConfig.getClientIdCode());
        headerMap.put(naverConfig.getSecretStr(), naverConfig.getSecretCode());
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");
        HttpHeaders header = httpRequest.createHeader(headerMap);

        String searchUrl = null;
        try {
            searchUrl = naverConfig.getSearchUrl() + URLEncoder.encode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonByUrl = httpRequest.getJsonByUrl(searchUrl, HttpMethod.POST, header);
        if (jsonByUrl == null) {
            return null;
        }
        JSONObject message = (JSONObject)jsonByUrl.get("message");
        JSONObject result = (JSONObject)message.get("result");
        String translatedText = (String)result.get("translatedText");

        return translatedText;
    }


}
