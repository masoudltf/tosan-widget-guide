package com.tosan.modern.widget.util;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

/**
 * @author Mohammad Abbasi
 * @since 16/02/2021
 */
public class JwtUtil {

    public static JwtClaims processJWT(String jwt) throws InvalidJwtException {
        JwtConsumerBuilder builder = new JwtConsumerBuilder();
        JwtConsumer jwtConsumer = builder.setSkipAllValidators().setDisableRequireSignature().setJwsAlgorithmConstraints(AlgorithmConstraints.NO_CONSTRAINTS).build();

        return jwtConsumer.processToClaims(jwt);
    }

}
