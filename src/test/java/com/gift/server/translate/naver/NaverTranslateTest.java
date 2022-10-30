package com.gift.server.translate.naver;

import com.gift.server.config.NaverConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NaverTranslateTest {

    @Autowired
    private NaverConfig config;

    @Test
    public void eng2korTest() {
        NaverTranslate naverTranslate = new NaverTranslate(config);
        String inp = "<p>I have a Svelte application with a Map component as below.  I'd like to pass in props for centreCoords and Zoom and then fly to them when they change outside the component.</p>\n" +
                "<pre><code>&lt;script&gt;\n" +
                "    import { onMount, onDestroy } from 'svelte';\n" +
                "    import mapboxgl from 'mapbox-gl';\n" +
                "    const token = 'xxx';\n" +
                "\n" +
                "\n" +
                "&lt;style&gt;\n" +
                "    @import 'mapbox-gl/dist/mapbox-gl.css';\n" +
                "    main div {\n" +
                "        height: 800px;\n" +
                "    }\n" +
                "&lt;/style&gt;\n" +
                "</code></pre>";


        Document parse = Jsoup.parse(inp);
        Elements select = parse.select("*");

        for (Element element : select) {
            if (!element.ownText().isEmpty()) {
                if (element.tagName().equals("code")) {
                    continue;
                }
                //element.text(naverTranslate.translate(element.ownText()));
            }
        }



        System.out.println("body = " + parse.body().html());

        System.out.println("inp = " + inp);

        String result = "<p>아래와 같이 맵 구성 요소가 있는 Svelte 어플리케이션을 가지고 있습니다. I%27d는 센터코드와 줌을 위한 소품을 전달한 후 구성 요소 외부에서 변경되면 소품으로 날아가고 싶습니다.</p> \n" +
                "<pre><code>&lt;script&gt;\n" +
                "    import { onMount, onDestroy } from 'svelte';\n" +
                "    import mapboxgl from 'mapbox-gl';\n" +
                "    const token = 'xxx';\n" +
                "\n" +
                "\n" +
                "&lt;style&gt;\n" +
                "    @import 'mapbox-gl/dist/mapbox-gl.css';\n" +
                "    main div {\n" +
                "        height: 800px;\n" +
                "    }\n" +
                "&lt;/style&gt;\n" +
                "</code></pre>";


    }

}