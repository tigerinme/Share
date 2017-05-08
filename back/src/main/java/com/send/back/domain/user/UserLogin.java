package com.send.back.domain.user;

/**
 * 功能描述：用户登录dao
 *
 * @author 董森
 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
 * @since 2017/5/8
 */
public class UserLogin {
    /**
     * 用户Id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 上一次登录时间
     */
    private String lastLoginTime;
    /**
     * 上一次登录Ip
     */
    private String lastLoginIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
}
