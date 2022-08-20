package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.util.JsonResult;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface IUserService {
    List getUserPermissionInfo(String token);
    int insertUser(User user,String token);
    Map findAll(String username,int pageNum,int pageSize) throws UnsupportedEncodingException;
    //删除用户
    int deleteUserByUid(int uid);
    //根据id修改用户信息
    int updateUserInfoByUid(User user,String token);
    //根据id查询用户
    JsonResult findByUid(Integer uid);
//    //按名字搜索
//    List<User> findUserByUserName(String username) throws UnsupportedEncodingException;
}
