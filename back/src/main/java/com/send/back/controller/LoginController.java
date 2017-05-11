package com.send.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.send.back.domain.response.Result;
import com.send.back.domain.response.Success;
import com.send.back.domain.user.SessionUser;
import com.send.back.domain.user.UserLogin;
import com.send.back.service.inter.UserService;
import com.send.back.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("user")
public class LoginController extends  BaseController{

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;



    @RequestMapping("goToLogin")
    public String goToLogin(){
        return "user/login";
    }

    /**
     * 功能描述：用户登录处理
     *
     * @param session
     * @param request
     * @param response
     * @param username   用户名
     * @param password   密码
     * @param rememberMe 记住我
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    @ResponseBody
    @RequestMapping("login")
    public Result login(HttpSession session,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        String username,
                        String password,
                        String rememberMe) {

        final String REMEMBER_ME = "1";
        String jsessionId = session.getId();
        logger.info("请求的sessionId:{}", jsessionId);
        UserLogin userLogin;
        Result result = userService.login(username, password, this.getIpAddr(request));
        if(result.getStatus() == 0){
            return result;
        }else{
            userLogin = (UserLogin) result.getReturnMessage();
            SessionUser sessionUser = new SessionUser();
            sessionUser.setUserId(userLogin.getId());
            sessionUser.setUsername(userLogin.getUsername());
            session.setAttribute(Constants.SESSION_USER_KEY, sessionUser);
        }
        //记住登陆状态
        if (REMEMBER_ME.equals(rememberMe)) {
            // 自动登录，保存用户名密码到 Cookie
            String infor = null;
            try {
                infor = URLEncoder.encode(username, "utf-8") + "|" + userLogin.getPassword();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            // 清除之前的Cookie 信息
            Cookie cookie = new Cookie(Constants.COOKIE_USER_INFO, null);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            // 建用户信息保存到Cookie中
            cookie = new Cookie(Constants.COOKIE_USER_INFO, infor);
            cookie.setPath("/");
            // 设置最大生命周期为1天。
            cookie.setMaxAge(1*24*60*60);
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie(Constants.COOKIE_USER_INFO, null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return new Success("登录成功");
    }

    /**
     * 功能描述：用户注册处理
     *
     * @param
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    @ResponseBody
    @RequestMapping("register")
    public Result register(HttpSession session,
                       HttpServletRequest request,
                       String username,
                       String email,
                       String password) {
        //获取用户Ip地址
        String ip = this.getIpAddr(request);
        UserLogin userLogin = new UserLogin();
        userLogin.setLastLoginIp(ip);
        userLogin.setUsername(username);
        userLogin.setPassword(password);
        userLogin.setEmail(email);
        //注册用户
        Result result = userService.register(userLogin);
        //注册用户失败
        if(result.getStatus()==0){
            return result;
        }else{//把用户信息加入session
            SessionUser sessionUser = new SessionUser();
            sessionUser.setUserId(userLogin.getId());
            sessionUser.setUsername(userLogin.getUsername());
            session.setAttribute(Constants.SESSION_USER_KEY, sessionUser);
        }
        return new Success();
    }


    /**
    * 功能描述： 校验用户姓名是否存在
    * @param
    * @return
    * @author 董森
    * @since 2017/5/10
    * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    @ResponseBody
    @RequestMapping("checkUsername")
    public String checkUsername(String username){
        System.out.println(username);
        JSONObject jsonObject = new JSONObject();
     if(userService.findUserByUserName(username)!= null){
         jsonObject.put("valid",false);
     }else{
         jsonObject.put("valid",true);
     }
     return jsonObject.toJSONString();
    }

    /**
     * 功能描述： 校验用户邮箱是否存在
     * @param
     * @return
     * @author 董森
     * @since 2017/5/10
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     */
    @ResponseBody
    @RequestMapping("checkEmail")
    public String checkEmail(String email){
        JSONObject jsonObject = new JSONObject();
        if(userService.findUserByEmail(email)!=null){
            jsonObject.put("valid",false);
        }else{
            jsonObject.put("valid",true);
        }
        return jsonObject.toJSONString();
    }

}
