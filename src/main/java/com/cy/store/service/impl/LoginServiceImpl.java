package com.cy.store.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cy.store.entity.LoginUser;
import com.cy.store.entity.Menu;
import com.cy.store.entity.User;
import com.cy.store.mapper.MenuMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.LoginService;
import com.cy.store.util.JsonResult;
import com.cy.store.util.JwtUtil;
import com.cy.store.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    private static final Integer OK = 200;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public JsonResult login(User user) {
        System.out.println(user);
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassWord());
        Authentication authentication=authenticationManager.authenticate(authenticationToken);
        System.out.println(authentication);
        if (Objects.isNull(authentication)){
//            throw new RuntimeException("登陆失败");
            return new JsonResult(300,"登录失败");
        }
        //生成jwt
        LoginUser loginUser=(LoginUser) authentication.getPrincipal();
        String jsonString=JSON.toJSONString(authentication.getPrincipal());
        String userid=loginUser.getUser().getUid().toString();
        String jwt= JwtUtil.createJWT(userid);
        Map<String,String> map2=new HashMap<>();
        map2.put("token",jwt);
        //v2.将用户信息存入到redis
        redisCache.setCacheObject("login:"+userid,jsonString);
        return new JsonResult(OK,map2);
    }
}
