package com.back.black.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.back.black.entity.User;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Component
public class JwtUtil {
    @Value("${jwt.key}")
    String jwt_key;
    @Value("${jwt.expiration}")
    int expireTime;//过期时间 小时

    public String createJWT(User user){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",user.getId());
        claims.put("username",user.getUsername());
        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())//随机生成uuid
                .withClaim("claim",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+ 1000L *60*60*expireTime))//过期时间
                .withIssuedAt(new Date())//创建时间
                .sign(Algorithm.HMAC256(jwt_key));//指定加密算法
    }

    //解析token
    public Map<String,Object> resolverJwt(String token){
        if(token==null) return null;
        Algorithm algorithm = Algorithm.HMAC256(jwt_key);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();//给他加密的算法
        try{//防止用户改token

            DecodedJWT verify = jwtVerifier.verify(token);
//            if(this.invaildateJwt(verify.getId())){//如果用户退出进入黑名单
//                return null;
//            }
            //看看有没有过期
            Date expiresAt = verify.getExpiresAt();
            return new Date().after(expiresAt) ? null : verify.getClaim("claim").asMap();

        }catch (JWTVerificationException e){
            return null;
        }

    }

}
