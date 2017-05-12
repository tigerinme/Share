package com.send.back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
* 功能描述： 首页
* @param
* @return
* @author 董森
* @since 2017/4/25
* @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
*/
@Controller
public class HomeController {

    /**
    * 功能描述：进入系统首页
    * @param
    * @return
    * @author 董森
    * @since 2017/4/25
    * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(null != cookies) {
            for (Cookie c : cookies) {
                System.out.println(c.getName() + "--->" + c.getValue());
            }
        }
        return "index";
    }
}
