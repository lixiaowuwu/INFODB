package com.cy.store.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class JwtUtil {
    private static final String SING="1!2@3#4$78";
    //有效期为
    public static final Long JWT_TTL = 60 * 60 * 24 * 7 * 1000L;// 60 * 60 *1000  一个小时
    public static String getUUID(){
    String token = UUID.randomUUID().toString().replaceAll("-", "");
    return token;
        }

    public static String createJWT(String subject) {
        String token = Jwts.builder().setSubject(subject).
                setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 60 * 24 * 7 * 1000)).
                signWith(SignatureAlgorithm.HS256,SING).
                compact();
        return token;
    }
    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getMimeDecoder().decode(JwtUtil.SING);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
    public static String parseToken(String token) {
        Claims body = Jwts.parser().setSigningKey(SING).parseClaimsJws(token).getBody();
        String subject = body.getSubject();
        return subject;
    }
}