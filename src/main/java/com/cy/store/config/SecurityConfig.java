package com.cy.store.config;

import com.cy.store.handler.AccessDeniedHandlerImpl;
import com.cy.store.handler.AuthenticationEntryPointImpl;
import com.cy.store.interceptor.JwtAuthenticationTokenFilter;
import com.cy.store.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//开启权限配置
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    //加密
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    //配置异常处理器
    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;
    @Bean
    public PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    protected void configure(HttpSecurity http) throws Exception{

        http
            //关闭csrf
            .csrf().disable()
            //不通过Session获取SecurityContext
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             //和
            .and()
                .authorizeRequests()
                //对于登录接口允许匿名访问
                .antMatchers("/users/login").anonymous()
                .antMatchers("/users/reg").anonymous()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationTokenFilter(),UsernamePasswordAuthenticationFilter.class);
//        http.authorizeRequests().antMatchers("/login/login").permitAll().antMatchers("/*").authenticated()
//                .and()
//                .formLogin();;

        http.exceptionHandling()
                //认证失败
                .authenticationEntryPoint(authenticationEntryPoint)
                //授权失败
                .accessDeniedHandler(accessDeniedHandler);

        http.cors();
    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //加入数据库验证类，下面的语句实际上在验证链中加入了一个DaoAuthenticationProvider
        auth.userDetailsService(userDetailsService);
    }

//    public void configure(WebSecurity web) {
//        //解决静态资源被拦截的问题
//        web.ignoring().antMatchers("/users/login");
//    }
}
