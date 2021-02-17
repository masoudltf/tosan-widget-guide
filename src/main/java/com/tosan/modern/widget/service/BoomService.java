package com.tosan.modern.widget.service;

import com.tosan.modern.widget.model.Customer;
import com.tosan.modern.widget.model.CustomerDeposit;
import com.tosan.modern.widget.model.Deposit;
import com.tosan.modern.widget.util.HttpHeadersAdapter;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * @author Mohammad Abbasi
 * @since 16/02/2021
 */
@Component
public class BoomService {

    private final RestTemplate restTemplate;

    @Value("${boom.baseurl}")
    private String baseUrl;

    @Value("${boom.boomLoginUrl}")
    private String loginUrl;

    @Value("${boom.depositUrl}")
    private String depositUrl;

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

    public BoomService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public Customer login(String userAgent, String bankCode, String loginToken, String ssoToken) {
        JSONObject jsonObject = setupJsonObject(ssoToken);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = HttpHeadersAdapter
                .builder()
                .userAgent(userAgent)
                .clientPlatformType(this.clientPlatformType)
                .acceptLanguage(this.acceptLanguage)
                .clientUserId(this.clientUserId)
                .appKey(this.appKey)
                .deviceId(this.deviceId)
                .bankCode(bankCode)
                .loginToken(loginToken)
                .build();
        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<Customer> responseEntityStr = restTemplate.postForEntity(this.baseUrl + this.loginUrl, request, Customer.class);
        Customer customerInfo = responseEntityStr.getBody();
        Objects.requireNonNull(customerInfo).setBankCode(bankCode);
        customerInfo.setLoginToken(loginToken);

        return customerInfo;
    }

    public Deposit[] getDeposits(String userAgent, String bankCode, String loginToken, String session) {
        HttpHeaders headers;
        headers = HttpHeadersAdapter
                .builder()
                .userAgent(userAgent)
                .clientPlatformType(this.clientPlatformType)
                .acceptLanguage(this.acceptLanguage)
                .clientUserId(this.clientUserId)
                .appKey(this.appKey)
                .deviceId(this.deviceId)
                .bankCode(bankCode)
                .loginToken(loginToken)
                .session(session)
                .build();
        HttpEntity<String> request = new HttpEntity<>(new JSONObject().toString(), headers);
        ResponseEntity<CustomerDeposit> responseEntityStr = restTemplate.postForEntity(this.baseUrl + this.depositUrl, request, CustomerDeposit.class);

        return (Objects.requireNonNull(responseEntityStr.getBody())).getDeposits();
    }

    private JSONObject setupJsonObject(String ssoToken) {
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("token", ssoToken);

        return new JSONObject(jsonMap);
    }

}
