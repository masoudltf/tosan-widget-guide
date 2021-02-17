package com.tosan.modern.widget.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mohammad Abbasi
 * @since 16/02/2021
 */
public class HttpRequestUtils {

    private static final String X_Forwarded_For = "X-Forwarded-For";
    private static final String Proxy_Client_IP = "Proxy-Client-IP";
    private static final String WL_Proxy_Client_IP = "WL-Proxy-Client-IP";
    private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    private static final String HTTP_X_FORWARDED = "HTTP_X_FORWARDED";
    private static final String HTTP_X_CLUSTER_CLIENT_IP = "HTTP_X_CLUSTER_CLIENT_IP";
    private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    private static final String HTTP_FORWARDED_FOR = "HTTP_FORWARDED_FOR";
    private static final String HTTP_FORWARDED = "HTTP_FORWARDED";
    private static final String HTTP_VIA = "HTTP_VIA";
    private static final String REMOTE_ADDR = "REMOTE_ADDR";
    private static final String DEFAULT_IP = "0.0.0.0";
    private static final String UNKNOWN = "unknown";

    private static final String[] IP_HEADER_CANDIDATES = new String[]{
            X_Forwarded_For,
            Proxy_Client_IP,
            WL_Proxy_Client_IP,
            HTTP_X_FORWARDED_FOR,
            HTTP_X_FORWARDED,
            HTTP_X_CLUSTER_CLIENT_IP,
            HTTP_CLIENT_IP,
            HTTP_FORWARDED_FOR,
            HTTP_FORWARDED,
            HTTP_VIA,
            REMOTE_ADDR
    };

    public static String getClientIpAddressIfServletRequestExist() {
        if (RequestContextHolder.getRequestAttributes() == null)
            return DEFAULT_IP;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        for (String header : IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(header);
            if (ipList != null && ipList.length() != 0 && !UNKNOWN.equalsIgnoreCase(ipList)) {
                return ipList.split(",")[0];
            }
        }

        return request.getRemoteAddr();
    }

}
