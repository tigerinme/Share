package com.send.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("personal")
    public String goToPersonal() {
        return "/user/personal";
    }

    @RequestMapping("setup")
    public String goToSetup() {
        return "/user/setup";
    }

    @RequestMapping("feedback")
    public String goToFeedback() {
        return "/common/feedback";
    }

}
