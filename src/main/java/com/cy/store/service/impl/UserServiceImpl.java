package com.cy.store.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cy.store.entity.LoginUser;
import com.cy.store.entity.Menu;
import com.cy.store.entity.User;
import com.cy.store.interceptor.JwtAuthenticationTokenFilter;
import com.cy.store.mapper.MenuMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.PasswordNotMatchException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.service.ex.UserNotFoundException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import com.cy.store.util.JwtUtil;
import com.cy.store.util.RedisCache;
import com.cy.store.util.SpringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RedisCache redisCache;
    @Autowired
    UserMapper userMapper;
    @Autowired
    MenuMapper menuMapper;
    /**
     * 通过token获取用户权限信息
     */
    @Override
    public List getUserPermissionInfo(String token) {
        String userid;
        try {
             userid = JwtUtil.parseToken(token);
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }
        //从redis获取用户信息
        String redisKey="login:"+userid;
        String redisString= redisCache.getCacheObject(redisKey);
        LoginUser loginUser = JSONObject.parseObject(redisString, LoginUser.class);
        List<Menu> permissionList =menuMapper.findpermsByUserId(loginUser.getUser().getUid());
        return permissionList;
    }
    /**
     * 注册
     */
    @Override
    public int insertUser(User user,String token) {
        if (userMapper.findByUsername(user.getUserName())!=null){
            throw new UsernameDuplicatedException("用户名已存在");
        }
        String userid;
        try {
            userid = JwtUtil.parseToken(token);
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }
        String encodePassword = passwordEncoder.encode(user.getPassWord());
        user.setUserName(user.getUserName());
        user.setPassWord(encodePassword);
        user.setIsDelete(0);
        //从redis获取用户信息
        String redisKey="login:"+userid;
        String redisString= redisCache.getCacheObject(redisKey);
        LoginUser loginUser = JSONObject.parseObject(redisString, LoginUser.class);
        Date data=new Date();
        user.setCreatedUser(loginUser.getUsername());
        user.setCreatedTime(data);
        user.setModifiedUser(loginUser.getUsername());
        user.setModifiedTime(data);
        Integer rows = userMapper.insert(user);
        return rows;
    }
    /*
        查询所有用户，username默认为空则查询所有用户。username带参为搜索功能like。
        包含分页查询，统计总数
     */
    @Override
    public Map findAll(String username,int pageNum,int pageSize){
        Map map=new HashMap();
        PageHelper.startPage(pageNum,pageSize);
        List<User> alluser = userMapper.findAll(username);
        PageInfo<User> pageInfo = new PageInfo<>(alluser);
        long total = pageInfo.getTotal();
        List<User> pageList = pageInfo.getList();
        map.put("total",total);
        map.put("list",pageList);
        return map;
    }
    //根据id删除用户
    @Override
    public int deleteUserByUid(int uid) {
        Integer isDelete = userMapper.deleteUserByUid(uid);
        return isDelete;
    }
    //修改用户信息
    @Override
    public int updateUserInfoByUid(User user,String token) {
        if (userMapper.findByUsername(user.getUserName())!=null){
            throw new UsernameDuplicatedException("用户名已存在");
        }
        String userid;
        try {
            userid = JwtUtil.parseToken(token);
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }
        String newPassWord = passwordEncoder.encode(user.getPassWord());
        user.setPassWord(newPassWord);
        user.setUserName(user.getUserName());
        user.setGender(user.getGender());
        user.setAvatar(user.getAvatar());
        String redisKey="login:"+userid;
        String redisString= redisCache.getCacheObject(redisKey);
        LoginUser loginUser = JSONObject.parseObject(redisString, LoginUser.class);
        Date data=new Date();
        user.setModifiedUser(loginUser.getUsername());
        user.setModifiedTime(data);
        Integer isUpdate = userMapper.updateUserInfoByUid(user);
        return isUpdate;
    }
    /**
    * @Description: 根据id进行查询
    * @Param:
    * @return:
    * @Author: xiao
    * @Date:
    */
    @Override
    public JsonResult findByUid(Integer uid) {
        User user = userMapper.findByUid(uid);
        if (user==null){
            return new JsonResult(300,"用户不存在，请刷新后重试",user);
        }
        return new JsonResult(200,"查询完成",user);
    }
}
