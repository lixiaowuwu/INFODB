package com.cy.store.service;

import com.cy.store.entity.ActNotice;

public interface IActUserService {
    //增加通知
    int insertNotice(ActNotice actNotice);
    //根据aid查询
    ActNotice findByAid(Integer aid);
}
