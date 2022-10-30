package com.gift.server.http;

import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HttpRequest {

    public boolean request(String url, HttpMethod httpMethod, String body) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate template = new RestTemplate(clientHttpRequestFactory);
        String response = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<String> entity = new HttpEntity<String>( body, headers);
            ResponseEntity<String> exchange = template.exchange(url, httpMethod, entity, String.class);
            response = exchange.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    public JSONObject getJsonByUrl(String url, HttpMethod httpMethod) {
        return getJsonByUrl(url, httpMethod, null);
    }
    public JSONObject getJsonByUrl(String url, HttpMethod httpMethod, HttpHeaders headers) {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate template = new RestTemplate(clientHttpRequestFactory);
        String response = null;

        try {
            /*
            if (headers != null) {
                HttpEntity<String> entity = new HttpEntity<String>("", headers);
                ResponseEntity<String> exchange = template.exchange(url, httpMethod, entity, String.class);
                response = exchange.getBody();
            } else {
                response = template.getForObject(url, String.class);
            }
             */
            HttpEntity<String> entity = new HttpEntity<String>("", headers);
            ResponseEntity<String> exchange = template.exchange(url, httpMethod, entity, String.class);
            response = exchange.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println(e.toString());
            return null;
        }


        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        try {
            return  (JSONObject)jsonParser.parse(response);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpHeaders createHeader(Map<String, String> headerKeyVal) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (Map.Entry<String, String> stringStringEntry : headerKeyVal.entrySet()) {
            headers.set(stringStringEntry.getKey(), String.valueOf(stringStringEntry.getValue()));
        }

        return headers;
    }

}
