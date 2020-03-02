package com.rafagao.dbook.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 需要写的信息
 * {
 * iat:签发时间
 * exp:过期时间
 * adu:受众（写用户的id）
 * }
 *
 * @author rafa gao
 */


public class JWTUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);


    //密钥
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    //
    private static final long TIME = 1000 * 60 * 60 * 24 * 7;
    //加密算法
    private static final SignatureAlgorithm SIG_ALG = SignatureAlgorithm.HS512;

    private static final String EXPIRATION = "EXPIRATION";

    /**
     * 当返回值为null时说明生成失败
     *
     * @param userName 需要添加进Token中的字段以及值
     * @return Token 生成的JWT
     */
    public static String generateToken(String userName) {
        String token = null;
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + TIME);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(EXPIRATION, expiration);
        try {
            token = Jwts.builder()
                    .setAudience(userName)
                    .setClaims(map)
                    .setExpiration(expiration)
                    .signWith(KEY, SIG_ALG)
                    .compact();
        } catch (InvalidKeyException e) {
            LOGGER.info("generateToken方法生成Token错误", e);
        }
        return token;
    }


    /**
     * 拿到Token当中的Claims键值对
     * 一旦返回null，就说明Token被人篡改了
     *
     * @param token Token字符串
     * @return Claims Claims键值对
     */
    private static Claims getClaims(String token) {
        if (token == null) {
            return null;
        }
        Claims claimsJws = null;
        try {
            claimsJws = Jwts.parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (RuntimeException e) {
            LOGGER.error("解析Token错误", e);
        }

        return claimsJws;
    }

    /**
     * 当返回为null的时候说明用户ID不存在
     *
     * @param
     * @return
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getAudience();
    }

    //当Claims为空时 或者claims为空时返回null
    private static String getUsernameByClaims(Claims claims) {
        if (claims != null) {
            return claims.getAudience();
        }
        return null;
    }



    /**
     * 验证Token是否有效(用户名以及过期时间)
     *
     * @param
     * @return
     */
    public static boolean validateToken(String token, UserDetails userDetails) {

        Claims claims = getClaims(token);

        if (!isExpiredByClaims(claims)) {
            String usernameFromClaims = getUsernameByClaims(claims);
            String username = userDetails.getUsername();
            if (username != null && usernameFromClaims != null) {
                return username.equals(usernameFromClaims);
            }
        }
        return false;
    }


    /**
     * 通过Claims判断
     *
     * @param
     * @return
     */
    private static boolean isExpiredByClaims(Claims claims) {
        if (claims != null) {
            Date date = claims.get(EXPIRATION, Date.class);
            if (date == null) {
                LOGGER.info("过期时间不存在");
            } else {
                return date.before(new Date());
            }
        }
        return false;
    }

    /**
     * 超时时返回true
     *
     * @param
     * @return
     */
    public static boolean isExpired(String token) {

        Claims claims = getClaims(token);
        if (claims.getExpiration().after(new Date())) {
            return false;
        }
        return true;

    }

    /**
     * 如果返回值为null,说明刷新失败
     *
     * @param token
     * @return
     */
    public static String rfreshExpiration(String token) {
        if (token == null) {
            return null;
        }
        String newToken = null;

        Claims claims = getClaims(token);
        if (claims.containsKey(Claims.AUDIENCE)) {
            String audience = claims.getAudience();
            if (claims.containsKey(Claims.EXPIRATION)) {
                newToken = generateToken(audience);
            }
        }

        return newToken;
    }

}
