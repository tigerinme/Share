package com.send.back.controller;

import com.send.back.domain.user.SessionUser;
import com.send.back.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/5/8.
 */
public class BaseController {
    public void setUserBaseInfo(Class<?> classz, Object obj, HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
        Integer userId = sessionUser.getUserId();
        String userName = sessionUser.getUsername();

        //反射
        try {
            Method userIdMethod = classz.getDeclaredMethod("setUserId", Integer.class);
            userIdMethod.invoke(obj, userId);
            Method userNameMethod = classz.getDeclaredMethod("setUserName", String.class);
            userNameMethod.invoke(obj, userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
    * 功能描述：session中获取用户id
    * @param session
    * @return
    * @author 董森
    * @since 2017/5/8
    * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    public Integer getUserId(HttpSession session) {
        Object sessionObj = session.getAttribute(Constants.SESSION_USER_KEY);
        if (null != sessionObj) {
            SessionUser sessionUser = (SessionUser) sessionObj;
            return sessionUser.getUserId();
        }
        return null;
    }

    /**
    * 功能描述：session中获取用户信息
    * @param  session
    * @return
    * @author 董森
    * @since 2017/5/8
    * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    public SessionUser getSessionUser(HttpSession session) {
        Object sessionObj = session.getAttribute(Constants.SESSION_USER_KEY);
        if (null != sessionObj) {
            SessionUser sessionUser = (SessionUser) sessionObj;
            return sessionUser;
        }
        return null;
    }

    /**
     * 获取用户等等Ip
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
