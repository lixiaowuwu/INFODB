package com.cy.store.controller;

import com.cy.store.entity.ActNotice;
import com.cy.store.service.IActUserService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/ActUser")
public class ActUserController {
    @Autowired
    private IActUserService actUserService;
    @RequestMapping("/insertNotice")
    public JsonResult insertNotice(@RequestBody ActNotice actNotice){
        int i = actUserService.insertNotice(actNotice);
        if (i==1){
            return new JsonResult(200,"添加成功");
        }
        return new JsonResult(300,"添加失败");
    }
    @RequestMapping("/findByAid")
    public JsonResult findByAid(@RequestBody Map map){
        int aid=(int)map.get("aid");
        ActNotice actNotice = actUserService.findByAid(aid);
        return new JsonResult(200,"查询成功",actNotice);
    }
}
