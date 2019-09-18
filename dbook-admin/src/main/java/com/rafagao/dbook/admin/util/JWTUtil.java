package com.rafagao.dbook.admin.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;

/**
 *
 * 需要写的信息
 * {
 *     iat:签发时间
 *     exp:过期时间
 *     adu:受众（写用户的id）
 * }
 * @author rafa gao
 */


public class JWTUtil {



    //密钥
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    //
    private static final long TIME=1000*60*60*24*7;


    /**
     * 当返回值为null时说明生成失败
     *
     * @param userID 需要添加进Token中的字段以及值
     * @return Token 生成的JWT
     */
    public static String generateToken(String userID)  {
        String token=null;
        Date issuedAt=new Date();
        Date expiration=new Date(issuedAt.getTime()+TIME);

        try {
            token= Jwts.builder()
                    .setAudience(userID)
                    .setIssuedAt(issuedAt)
                    .setExpiration(expiration)
                    .signWith(KEY, SignatureAlgorithm.HS512)
                    .compact();
        } catch (InvalidKeyException e) {
            e.getStackTrace();
        }

        return token;
    }



    /**
     * 拿到Token当中的Claims键值对
     * 一旦有异常被捕获，就说明Token被人篡改了
     *
     * @param token Token字符串
     * @return Claims Claims键值对
     */

    public static Claims getClaims(String token){

        if (token==null){
            return null;
        }

        Jws<Claims> claimsJws = null;

        try {
            claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return claimsJws.getBody();
    }


    public static boolean validateToken(String token){

        Claims claims = getClaims(token);
        return getClaims(token)!=null;
    }

    /**
     * 超时时返回true
     * @param
     * @return
     */
    public static boolean isExpired(String token){

        Claims claims = getClaims(token);
        if (claims.getExpiration().after(new Date())){
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
    public static String rfreshExpiration(String token){
        if (token==null){
            return null;
        }
        String  newToken=null;

        Claims claims=getClaims(token);
        if (claims.containsKey(Claims.AUDIENCE)){
            String audience = claims.getAudience();
            if(claims.containsKey(Claims.EXPIRATION)){
                newToken=generateToken(audience);
            }
        }

        return newToken;
    }

}
