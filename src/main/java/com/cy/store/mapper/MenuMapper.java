package com.cy.store.mapper;

import com.cy.store.entity.Menu;

import java.util.List;

public interface MenuMapper {
    //前端查询权限信息接口
    List<Menu> findpermsByUserId(long id);

    List<String> selectpermsByUserId(long id);
}
