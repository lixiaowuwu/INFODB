package com.cy.store.service.impl;

import com.cy.store.entity.ActNotice;
import com.cy.store.mapper.ActNoticeMapper;
import com.cy.store.service.IActNoticeService;
import com.cy.store.service.IActUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActNoticeServiceImpl implements IActNoticeService {
    @Autowired
    ActNoticeMapper actNoticeMapper;
    @Override
    public int insertNotice(ActNotice actNotice) {
        int i = actNoticeMapper.insertNotice(actNotice);
        return i;
    }

    @Override
    public ActNotice findByAid(Integer aid) {
        ActNotice byAid = actNoticeMapper.findByAid(aid);
        return byAid;
    }
}
