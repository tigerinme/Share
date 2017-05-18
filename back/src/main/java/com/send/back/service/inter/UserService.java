package com.send.back.service.inter;



import com.send.back.domain.response.Result;
import com.send.back.domain.share.Share;
import com.send.back.domain.user.UserLogin;

import java.util.List;


public interface UserService {
    /**
     * 功能描述：注册用户
     *
     * @param user
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    Result register(UserLogin user);

    /**
     * 功能描述：
     *
     * @param username
     * @param password
     * @param ip
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    Result login(String username, String password, String ip);

    /**
     * 功能描述：根据用户名查找用户
     *
     * @param username
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    UserLogin findUserByUserName(String username);

    /**
     * 功能描述：根据邮箱查找用户
     *
     * @param email
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    UserLogin findUserByEmail(String email);

    /**
     * 更新用户登录Ip和登录时间
     *
     * @param ip
     */
    void updateLastLoginInfo(String ip, Integer id);

    /**
     * 功能描述：查询我的分享
     *
     * @param page
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/18
     */
    List<Share> getMyShare(Integer page, Integer userId);
}

