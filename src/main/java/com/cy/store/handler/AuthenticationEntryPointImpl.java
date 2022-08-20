package com.cy.store.handler;

import com.alibaba.fastjson.JSON;
import com.cy.store.util.JsonResult;
import com.cy.store.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //处理异常
        JsonResult jsonResult=new JsonResult(HttpStatus.UNAUTHORIZED.value(),"淦，又报错了");
        String json = JSON.toJSONString(jsonResult);
        WebUtils.renderString(response,json);
    }
}
