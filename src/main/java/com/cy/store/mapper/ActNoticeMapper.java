package com.cy.store.mapper;

import com.cy.store.entity.ActNotice;

public interface ActNoticeMapper {
    //增加通知
    int insertNotice(ActNotice actNotice);
    //根据aid查询
    ActNotice findByAid(Integer aid);
}
