package com.izzi.integra.console.security;

import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rafael on 27/12/2016.
 */
public class TokenAuthHelper {

    public static String getTokenAuth() {
        final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600000L);
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "mySecret");

        final Map<String, Object> claims = new HashMap();
        claims.put(JwtTokenUtil.CLAIM_KEY_USERNAME, "rafael.briones.ext");
        claims.put(JwtTokenUtil.CLAIM_KEY_AUDIENCE, "web");
        claims.put(JwtTokenUtil.CLAIM_KEY_CREATED, new Date());

        return jwtTokenUtil.generateToken(claims);
    }

}
