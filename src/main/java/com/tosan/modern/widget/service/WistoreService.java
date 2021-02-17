package com.tosan.modern.widget.service;

import com.tosan.modern.widget.model.WistoreLoginToken;
import com.tosan.modern.widget.util.HttpHeadersAdapter;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Mohammad Abbasi
 * @since 16/02/2021
 */
@Service
public class WistoreService {

    private final RestTemplate restTemplate;
    private WistoreLoginToken wistoreLoginToken;

    @Value("${wistore.api.baseurl}")
    private String wistoreBaseUrl;

    @Value("${wistore.api.loginUrl}")
    private String wistoreLoginUrl;

    @Value("${wistore.userName}")
    private String wistoreUserName;

    @Value("${wistore.password}")
    private String wistorePassword;

    @Value("${clientPlatformType}")
    private String clientPlatformType;

    @Value("${acceptLanguage}")
    private String acceptLanguage;

    @Value("${clientUserId}")
    private String clientUserId;

    @Value("${appKey}")
    private String appKey;

    @Value("${clientDeviceId}")
    private String deviceId;

    public WistoreService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public WistoreLoginToken login(String userAgent) {
        JSONObject userObjectJson = getUserObjectJson();
        HttpHeaders headers = HttpHeadersAdapter.builder()
                .userAgent(userAgent)
                .clientPlatformType(this.clientPlatformType)
                .acceptLanguage(this.acceptLanguage)
                .clientUserId(this.clientUserId)
                .appKey(this.appKey)
                .deviceId(this.deviceId)
                .build();
        HttpEntity<String> request = new HttpEntity<>(userObjectJson.toString(), headers);
        ResponseEntity<WistoreLoginToken> responseEntityStr = restTemplate.postForEntity(this.wistoreBaseUrl + this.wistoreLoginUrl, request, WistoreLoginToken.class);

        return responseEntityStr.getBody();
    }

    private JSONObject getUserObjectJson() {
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("username", this.wistoreUserName);
        jsonMap.put("password", this.wistorePassword);

        return new JSONObject(jsonMap);
    }

    public WistoreLoginToken getWistoreLoginToken() {
        return wistoreLoginToken;
    }

    public void setWistoreLoginToken(WistoreLoginToken wistoreLoginToken) {
        this.wistoreLoginToken = wistoreLoginToken;
    }
}
