package com.back.black.interceptors;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.back.black.Util.JwtUtil;
import com.back.black.Util.RestBean;
import com.back.black.Util.TheadlocalUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
@Component
public class LoginIntercepter implements HandlerInterceptor {
    @Resource
    JwtUtil jwtUtil;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
//            DecodedJWT decodedJWT = jwtUtil.resolverJwt(token);
            Map<String, Object> map = jwtUtil.resolverJwt(token);
            //存到线程当中去
            TheadlocalUtil.set(map);
            if(map!=null){
                ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
                //检查redis中有没有token
                String redisToken = operations.get(token);
                if (redisToken==null){
                    throw new RuntimeException();
                }
            }

            return map != null;

        } catch (Exception e) {
            //token 有误
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TheadlocalUtil.remove();//防止内存泄露；
    }
}
