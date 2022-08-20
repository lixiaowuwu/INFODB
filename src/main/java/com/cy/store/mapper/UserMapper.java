package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

/**
 * 用户模块 持久性接口
 */
@Mapper
public interface UserMapper {
    //注册*
    Integer insert(User user);
    //
    User findByUsername(String username);
    //设置用户权限
    void setUserPermission(Integer uid);
    //查询所有用户
    List<User> findAll(String username);
//    /*
//        搜索按用户名称
//     */
//    List<User> findUserByUserName(String username);
    //删除用户
    Integer deleteUserByUid(Integer uid);
    //修改用户
    Integer updateUserInfoByUid(User user);
    /*
     * 获取当前登录的用户的信息
     * @param uid 当前登录的用户的id
     * @return 当前登录的用户的信息
     */
    User findByUid(Integer uid);
    Integer updatePasswordByUid(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
    /*
     * 获取当前登录的用户的信息
     * @param uid 当前登录的用户的id
     * @return 当前登录的用户的信息
     */

    /*
     * 修改用户资料
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @param user 用户的新的数据
     */


    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime
    );
}
