package com.send.back.domain.user;

/**
 * 功能描述：用户信息
 *
 * @author 董森
 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
 * @since 2017/5/8
 */
public class UserInfo {
    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户性别，0，男；1女，2未设置
     */
    private Integer sex;
    /**
     * 用户手机号
     */
    private Integer phoneNumber;
    /**
     * 用户个人简介
     */
    private String introduction;
    /**
     * 用户微信二维码
     */
    private String qrCodeWechat;
    /**
     * 用户支付宝二维码
     */
    private String qrCodeAlipay;
    /**
     * 用户等级，可以考虑2.0版本做
     */
    private Integer grade;
    /**
     * 用户个人信息更新时间
     */
    private String updateTime;
    /**
     * 用户创建是时间
     */
    private String createTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getQrCodeWechat() {
        return qrCodeWechat;
    }

    public void setQrCodeWechat(String qrCodeWechat) {
        this.qrCodeWechat = qrCodeWechat;
    }

    public String getQrCodeAlipay() {
        return qrCodeAlipay;
    }

    public void setQrCodeAlipay(String qrCodeAlipay) {
        this.qrCodeAlipay = qrCodeAlipay;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
