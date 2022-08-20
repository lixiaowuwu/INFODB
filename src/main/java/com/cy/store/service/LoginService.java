package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.util.JsonResult;

import java.io.UnsupportedEncodingException;

public interface LoginService {
    JsonResult login(User user);
}
