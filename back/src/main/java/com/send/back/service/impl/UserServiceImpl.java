package com.send.back.service.impl;

import com.send.back.dao.UserMapper;
import com.send.back.domain.response.Failed;
import com.send.back.domain.response.Result;
import com.send.back.domain.response.Success;
import com.send.back.domain.share.Share;
import com.send.back.domain.user.UserLogin;
import com.send.back.service.inter.UserService;
import com.send.back.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 功能描述：处理用户相关逻辑
 *
 * @author 董森
 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
 * @since 2017/5/8
 */

@Service("userService")
@PropertySource(value = "classpath:system.properties")
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Environment env;


    @Override
    public Result register(UserLogin userLogin) {
        String userName = userLogin.getUsername();
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();
        String md5key = env.getProperty("md5key");
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(email) || StringUtils.isEmpty(password) || userName.length() > Constants
                .LENGTH_20 || password.length() > Constants.LENGTH_16 || password.length() < Constants.LENGTH_6 || !StringTools.checkEmail
                (email) || !StringTools.checkUserName(userName) || !StringTools.checkPassWord(password)) {
            return new Failed("输入参数不合法");
        }

        /**
         * 校验用户是否已经存在
         */
        if (findUserByUserName(userName) != null) {
            return new Failed("用户名已经被使用");
        }
        /**
         * 校验邮箱是否已经存在
         */
        if (findUserByEmail(email) != null) {
            return new Failed("邮箱已经被使用");
        }
        //用户密码加密
        userLogin.setPassword(Md5Util.getMD5(password + md5key));
        userMapper.register(userLogin);
        return new Success("注册成功");
    }

    @Override
    public Result login(String username, String password, String ip) {
        String md5key = env.getProperty("md5key");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return new Failed("用户名或者密码参数错误");
        }
        UserLogin userLogin;
        //邮箱登陆
        if (username.contains("@")) {
            userLogin = this.findUserByEmail(username);
        } else {
            userLogin = this.findUserByUserName(username);
        }
        if (null == userLogin) {
            return new Failed("用户名错误");
        }
        if (!userLogin.getPassword().equals(Md5Util.getMD5(password + md5key))) {
            return new Failed("登录密码错误");
        }
        updateLastLoginInfo(ip, userLogin.getId());
        return new Success("登录成功", userLogin);
    }


    /**
     * 功能描述：根据用户名查找用户
     *
     * @param
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    @Override
    public UserLogin findUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }

    /**
     * 功能描述：根据邮箱查找用户
     *
     * @param email
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    @Override
    public UserLogin findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    /**
     * 功能描述：更新用户登录信息
     *
     * @param ip
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    public void updateLastLoginInfo(String ip, Integer id) {
        userMapper.updateLastLoginInfo(ip, id);
    }

    @Override
    public List<Share> getMyShare(Integer page,Integer userId) {
        Integer beginIndex;
        Integer pageSize = 10;
        if (page <= 1) {
            beginIndex = 0;
        } else {
            beginIndex = (page - 1) * 10;
        }
        List<Share> shareLists = userMapper.getMyShare(beginIndex, pageSize,userId);
        for(Share s:shareLists){
            String ct = s.getCreateTime();
            if(StringUtils.isNotBlank(ct)) {
                try {
                    //修改显示时间
                    ct = DateFormatUtil.format(DateFormatUtil.getDateFromString(ct, DateFormatUtil.FORMAT_TYPE1));
                    s.setCreateTime(ct);
                } catch (Exception e) {
                    logger.info("USerServiceImpl:getMyShare()日期转换异常,分享内容Id：{}", s.getId());
                }
            }
            List<String> tagsList = StringTools.getListFromString(s.getTags(),",");
            s.setTagList(tagsList);
        }
        return shareLists;
    }
}
