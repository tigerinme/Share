package com.send.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class LoginController {

    @RequestMapping("login")
    public String login(){
        System.out.println("login");
        return "user/login";
    }

    @RequestMapping("register")
    public String register(){
        System.out.println("register");
        return "user/login";
    }
}
