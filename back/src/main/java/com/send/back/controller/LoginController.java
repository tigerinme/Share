package com.send.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.send.back.domain.response.Result;
import com.send.back.domain.response.Success;
import com.send.back.domain.user.SessionUser;
import com.send.back.domain.user.UserLogin;
import com.send.back.service.inter.UserService;
import com.send.back.utils.Constants;
import com.send.back.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class LoginController extends  BaseController{

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;



    @RequestMapping("goToLogin")
    public ModelAndView goToLogin(String type,
                                  @RequestParam(required = false) String from){
        ModelAndView modelAndView = new ModelAndView("user/login");
        System.out.println(from);
        if(StringUtils.isNotEmpty(type)){
            if(type.equals("0")){//点击登陆
                modelAndView.addObject("type",0);
            }else{
                modelAndView.addObject("type",1);
            }
        }else{
            modelAndView.addObject("type",0);
        }
        if (StringUtils.isNoneEmpty(from)){
            modelAndView.addObject("from",from);
        }
        return modelAndView;
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
                        String rememberMe,
                        String from) {

//        String token2=request.getHeader("token");
//        Map<String,Object> result2 =TokenUtil.validToken(token2);
//        System.out.println(JSONObject.toJSON(result2).toString());

        Cookie[] cookies = request.getCookies();
        if(null != cookies) {
        for(Cookie c :cookies ){
            System.out.println(c.getName()+"--->"+c.getValue());
        }}

        final String REMEMBER_ME = "1";
        UserLogin userLogin;
        Result result = userService.login(username, password, this.getIpAddr(request));
        if(result.getStatus() == 0){
            return result;
        }else{
            //生成token
            userLogin = (UserLogin) result.getReturnMessage();
            Map<String , Object> payload=new HashMap<String, Object>();
            Date date=new Date();
            payload.put("uid", userLogin.getId());//用户id
            payload.put("iat", date.getTime());//生成时间
            payload.put("ext",date.getTime()+1000*60*60);//过期时间1小时
            String token= TokenUtil.createToken(payload);
            Cookie cookie=new Cookie("token", token);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            //生成session
            SessionUser sessionUser = new SessionUser();
            sessionUser.setUserId(userLogin.getId());
            sessionUser.setUsername(userLogin.getUsername());
            System.out.println(userLogin.getUsername());
            session.setAttribute(Constants.SESSION_USER_KEY, sessionUser);
        }
        //记住登陆状态
        if (REMEMBER_ME.equals(rememberMe)) {

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
                       HttpServletResponse response,
                       String username,
                       String email,
                       String password) {
        //获取用户Ip地址
        String ip = this.getIpAddr(request);
        UserLogin userLogin = new UserLogin();
        userLogin.setLastLoginIp(ip);
        userLogin.setUsername(username);
        userLogin.setPassword(password);
        userLogin.setEmail(email.toLowerCase());
        //注册用户
        Result result = userService.register(userLogin);
        //注册用户失败
        if(result.getStatus()==0){
            return result;
        }else{
            //生成token
            userLogin = (UserLogin) result.getReturnMessage();
            Map<String , Object> payload=new HashMap<String, Object>();
            Date date=new Date();
            payload.put("uid", userLogin.getId());//用户id
            payload.put("iat", date.getTime());//生成时间
            payload.put("ext",date.getTime()+1000*60*60);//过期时间1小时
            String token= TokenUtil.createToken(payload);
            Cookie cookie=new Cookie("token", token);
            cookie.setMaxAge(3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            //生成session
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

    @RequestMapping("goToShare")
    public String goToShare(){
        return "share";
    }


    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request,
                           HttpServletResponse response){

        return "";
    }
}
