package com.send.back.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 功能描述：字符串相关操作
 *
 * @author 董森
 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
 * @since 2017/5/8
 */
public class StringTools {

    /**
    * 功能描述：校验用户名合法性
    * @param username
    * @return
    * @author 董森
    * @since 2017/5/8
    * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    public static boolean checkUserName(String username) {
        String checkUserName = "^[\\w\\u4e00-\\u9fa5]+$";
        if (StringUtils.isNotEmpty(username)) {
            return username.matches(checkUserName);
        } else {
            return false;
        }
    }

    /**
    * 功能描述：校验用户密码
    * @param password
    * @return
    * @author 董森
    * @since 2017/5/8
    * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    public static boolean checkPassWord(String password) {
        String checkPassWord = "^[0-9a-zA-Z]+$";
        if (StringUtils.isNotEmpty(password)) {
            return password.matches(checkPassWord);
        } else {
            return false;
        }
    }
   /**
   * 功能描述：校验用户邮箱
   * @param email
   * @return
   * @author 董森
   * @since 2017/5/8
   * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
   */
    public static boolean checkEmail(String email) {
        String checkEmail = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
        if (StringUtils.isNotEmpty(email)) {
            return email.matches(checkEmail);
        } else {
            return false;
        }
    }
}
