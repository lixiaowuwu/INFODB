package com.cy.store.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginUser implements UserDetails{
    private User user;
    //第二部分，涉及到权限，需要将权限信息也封装到这里返回
    private List<String> permissions;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }
    //存入redis中不能被序列化
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;
    @Override
    //角色信息相关,苏然我们自己定义了集合存权限信息，但springsecurity是根据下面方法来获取权限信息
    //所以需要重写此方法，把自己设置的权限集合转为该方法需要的
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities!=null){
            return authorities;
        }
//             authorities=new ArrayList<>();
        //把permission中String的权限信息封装成SimleGrantedAuthority对象,从String转集合
//        for (String permissions:permissions){
//            SimpleGrantedAuthority authority=new SimpleGrantedAuthority(permissions);
//            authorities.add(authority);
//        }
        List<SimpleGrantedAuthority> authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassWord();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    //是否过期
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
