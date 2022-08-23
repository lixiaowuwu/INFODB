package com.cy.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActUser {
    private int aid;
    private String userName;
    private String passWord;
    private int phone;
    private String address;
    private String email;
    private String avatar;
    private int gender;
    private String scope;
    private int fansNum;
    private int isDelete;
    private int isLiving;
    private int noticed;
    private String views;
    private String reserve1;
    private String reserve2;
}
