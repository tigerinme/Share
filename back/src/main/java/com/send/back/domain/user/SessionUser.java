package com.send.back.domain.user;

/**
 * 功能描述：session中用户信息
 *
 * @author 董森
 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
 * @since 2017/5/8
 */
public class SessionUser {
    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
