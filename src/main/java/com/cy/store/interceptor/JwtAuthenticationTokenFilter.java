package com.cy.store.interceptor;

import com.alibaba.fastjson.JSON;
import com.cy.store.config.RedisConfig;
import com.cy.store.entity.LoginUser;
import com.cy.store.entity.Menu;
import com.cy.store.entity.User;
import com.cy.store.mapper.MenuMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.impl.UserDetailsServiceImpl;
import com.cy.store.util.JwtUtil;
import com.cy.store.util.RedisCache;
import com.cy.store.util.SpringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private static RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String userid = null;
        String token = request.getHeader("token");
        if (!StringUtils.hasLength(token)){
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
//        username = JwtUtil.getUsername(token,"username");
        try {
            userid =JwtUtil.parseToken(token);
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }
        //从redis获取用户信息
        String redisKey="login:"+userid;
        if (redisCache==null){
             redisCache = SpringUtils.getBean(RedisCache.class);
        }
        String cache=redisCache.getCacheObject(redisKey);
        LoginUser loginUser = JSON.parseObject(cache, LoginUser.class);
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
