package com.cy.store.mapper;

import com.cy.store.entity.ActNotice;
import com.cy.store.entity.ActUser;
import com.cy.store.service.IActUserService;
import com.cy.store.service.IUserService;
import com.cy.store.util.RedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ActMapperTests {
    @Autowired
    ActNoticeMapper actNoticeMapper;
    @Autowired
    ActUserMapper actUserMapper;
    @Autowired
    IActUserService actUserService;
    @Test
    public void contextLoads() throws Exception {
//        int insetActUser = actUserMapper.insetActUser(new ActUser(1, "小李", "123", 456, "123", "123", "123", 1, "123", 0, 1, 1, 123, "123", "123", "123"));
//        System.out.println(insetActUser);
//        Date date=new Date();
//        actNoticeMapper.insertNotice(new ActNotice(2,2,date,"123","123","123",0,"123","123"));
//        ActNotice byAid = actNoticeMapper.findByAid(1);
//        System.out.println(byAid);
        actUserService.findByAid(1);
    }
}
