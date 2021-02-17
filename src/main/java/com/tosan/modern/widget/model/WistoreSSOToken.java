package com.tosan.modern.widget.model;

import lombok.Builder;
import lombok.Getter;
import org.jose4j.jwt.JwtClaims;

import java.util.Map;
/**
 * @author Mohammad Abbasi
 * @since 16/02/2021
 */
@Getter
public class WistoreSSOToken {

    @Getter
    private final String ssoToken;

    private final String bankCode;

    public WistoreSSOToken(JwtClaims jwtClaims){
        Map<String, Object> map = jwtClaims.getClaimsMap();
        this.ssoToken = (String) map.get("ssoToken");
        this.bankCode = (String) map.get("bankCode");
    }

    @Builder
    public static WistoreSSOToken createInstance(JwtClaims jwtClaims){
        return new WistoreSSOToken(jwtClaims);
    }

}
