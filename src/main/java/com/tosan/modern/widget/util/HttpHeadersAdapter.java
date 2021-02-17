package com.tosan.modern.widget.util;

import lombok.Builder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;
/**
 * @author Mohammad Abbasi
 * @since 16/02/2021
 */
public class HttpHeadersAdapter extends HttpHeaders {

    private static final String CLIENT_IP_ADDRESS = "Client-Ip-Address";
    private static final String CLIENT_PLATFORM_TYPE = "Client-Platform-Type";
    private static final String CLIENT_USER_ID = "Client-User-Id";
    private static final String CLIENT_USER_AGENT = "Client-User-Agent";
    private static final String CLIENT_DEVICE_ID = "Client-Device-Id";
    private static final String ACCEPT_LANGUAGE = "Accept-Language";
    private static final String APP_KEY = "App-Key";
    private static final String DEVICE_ID = "Device-Id";
    private static final String BANK_ID = "Bank-Id";
    private static final String TOKEN_ID = "token-Id";
    private static final String SESSION = "session";

    private HttpHeadersAdapter(String userAgent,
                               String bankCode,
                               String loginToken,
                               String session,
                               String clientPlatformType,
                               String clientUserId,
                               String acceptLanguage,
                               String appKey,
                               String deviceId) {
        super();
        String clientIpAddress = HttpRequestUtils.getClientIpAddressIfServletRequestExist();
        setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        setContentType(MediaType.APPLICATION_JSON);
        add(CLIENT_IP_ADDRESS, clientIpAddress);
        add(CLIENT_PLATFORM_TYPE, clientPlatformType);
        add(CLIENT_USER_ID, clientUserId);
        add(CLIENT_USER_AGENT, userAgent);
        add(CLIENT_DEVICE_ID, clientIpAddress);
        add(ACCEPT_LANGUAGE, acceptLanguage);
        add(APP_KEY, appKey);
        add(DEVICE_ID, deviceId);

        if (bankCode != null) {
            add(BANK_ID, bankCode);
        }
        if (bankCode != null) {
            add(TOKEN_ID, loginToken);
        }
        if (session != null) {
            add(SESSION, session);
        }
    }

    @Builder()
    public static HttpHeadersAdapter createInstance(String userAgent,
                                                    String bankCode,
                                                    String loginToken,
                                                    String session,
                                                    String clientPlatformType,
                                                    String clientUserId,
                                                    String acceptLanguage,
                                                    String appKey,
                                                    String deviceId
    ) {
        return new HttpHeadersAdapter(userAgent,
                bankCode,
                loginToken,
                session,
                clientPlatformType,
                clientUserId,
                acceptLanguage,
                appKey,
                deviceId
        );
    }

}
