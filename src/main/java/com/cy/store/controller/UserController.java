package com.cy.store.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.LoginService;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import com.cy.store.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

//@Controller
@RestController
@RequestMapping("/users")
public class UserController extends BaseController{
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    IUserService userService;
    @Autowired
    private LoginService loginService;
    @PostMapping("/hello")
    @PreAuthorize("hasAnyAuthority('root')")
    public String reg() {
        return "hello";
    }
    @GetMapping("/getUserPermissionInfo")
    public List getUserPermissionInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        List list = userService.getUserPermissionInfo(token);
        return list;
    }
    @PostMapping(value = "/login")
    public JsonResult<User> login(@RequestBody User user)  {
//        request.getHeader("token");
//        System.out.println(user);
        JsonResult login = loginService.login(user);
        return login;
    }
    @RequestMapping("/insertUser")
    public JsonResult insertUser(@RequestBody User user,HttpServletRequest request){
        String token = request.getHeader("token");
        int isInsert = userService.insertUser(user,token);
        if (isInsert!=1){
            return new JsonResult(300,"添加失败");
        }
        return new JsonResult<>(200,"添加成功");
    }
    @RequestMapping("/findAll")
    @PreAuthorize("hasAnyAuthority('root')")
    public JsonResult findAll(@RequestBody Map map) throws UnsupportedEncodingException {
        String username=(String) map.get("userName");
        log.info("需要查询的名字为:"+username);
        int pageSize =(int) map.get("pageSize");
        log.info("从第几行开始："+pageSize);
        int pageNum=(int) map.get("pageNum");
        Map all = userService.findAll(username,pageNum,pageSize);
        return new JsonResult(200,"查询成功",all);
    }
    //根据id删除用户
    @RequestMapping("/deleteUserByUid")
    @PreAuthorize("hasAnyAuthority('root')")
    public JsonResult deleteUserByUid(@RequestBody Map map){
        int uid =(int)map.get("uid");
        int i = userService.deleteUserByUid(uid);
        if (i==0){
            return new JsonResult(300,"删除失败，该用户不存在");
        }
        return new JsonResult(200,"删除成功");
    }
    @RequestMapping("/updateUserInfoByUid")
    @PreAuthorize("hasAnyAuthority('root')")
    public JsonResult updateUserInfoByUid(@RequestBody User user,HttpServletRequest request){
        String token = request.getHeader("token");
        int isUpdate = userService.updateUserInfoByUid(user, token);
        if (isUpdate!=1){
            return new JsonResult(300,"修改用户信息出错");
        }
        return new JsonResult(200,"修改成功");
    }
    @RequestMapping("/findByUid")
    @PreAuthorize("hasAnyAuthority('root')")
    public JsonResult findByUid(@RequestBody Map map){
        return userService.findByUid((int)map.get("uid"));
    }
//    //名称搜索
//    @RequestMapping("/findUserByUserName")
//    @PreAuthorize("hasAnyAuthority('root')")
//    public JsonResult findUserByUserName(@RequestBody Map map) throws UnsupportedEncodingException {
//        String username=(String) map.get("username");
//        log.info("需要查询的名字为:"+username);
//        List<User> list = userService.findUserByUserName(username);
//        return new JsonResult(200,"查询成功",list);
//    }
}
