package com.send.back.dao;

import com.send.back.domain.share.Share;
import com.send.back.domain.user.UserLogin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface UserMapper {

    /**
     * 功能描述：注册用户
     *
     * @param userLogin
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    @Insert({"insert into user_login(username,email,password,last_login_time,last_login_ip)",
            "values",
//            "(#{userLogin.getUsername},#{userLogin.getEmail},#{userLogin.getPassword},now(),#{userLogin.getLastLoginIp})"
            "(#{username},#{email},#{password},now(),#{lastLoginIp})"
    })
    void register(UserLogin userLogin);


    /**
     * 功能描述：根据用户名查找注册用户信息
     *
     * @param username
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    @Select({"select id,username,password,last_login_time as lastLoginTime,last_login_ip as lastLoginIp,create_time as createTime",
            "from user_login ",
            "where username = #{username} ",
            "and delete_flag = 0"})
    UserLogin findUserByUserName(String username);


    /**
     * 功能描述：根据邮箱查找注册用户信息
     *
     * @param email
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    @Select({"select id,username,password,last_login_time as lastLoginTime,last_login_ip as lastLoginIp,create_time as createTime",
            "from user_login ",
            "where email = #{email} ",
            "and delete_flag = 0"})
    UserLogin findUserByEmail(String email);


    /**
     * 功能描述：更新用户ip 和 登录时间
     *
     * @param id
     * @param ip
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/8
     */
    @Update({"update user_login set last_login_ip = #{ip},last_login_time = now() where id = #{id}"})
    void updateLastLoginInfo(@Param("ip") String ip,
                             @Param("id") Integer id);


    /**
     * 功能描述：查询分享
     *
     * @param userId
     * @param beginIndex
     * @param pageSize
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/18
     */
    @Select({" SELECT ",
            " sc.id,sc.user_id AS userId,ui.nickname," ,
                    "title,content,tags,sc.create_time as createTime, ",
            " sc.can_comment AS canComment, ",
            "sc.summary,",
            "sc.img,",
            " IFNULL(sc.can_public,0) AS canPublic, ",
            " IFNULL(ss.view_count,0) as viewCount, ",
            " IFNULL(ss.comment_count,0) as commentCount, ",
            " IFNULL(ss.like_count,0) as likeCount, ",
            " IFNULL(ss.share_count,0) as shareCount, ",
            " IFNULL(ss.thumbup_count,0) as thumbUpCount, ",
            " IFNULL(ss.collection_count,0) as collectionCount, ",
            " IFNULL(ss.thumbdown_count,0) as thumbDownCount,",
            " ui.avatar as avatar ",
            " FROM ",
            " share_content sc ",
            " LEFT JOIN share_statistics ss ON ss.content_id = sc.id ",
            " LEFT JOIN user_info ui on sc.user_id = ui.user_id ",
            " WHERE",
            " sc.user_id =#{userId}",
            " limit #{beginIndex} ,#{pageSize}"})
    List<Share> getMyShare(@Param("beginIndex") Integer beginIndex,
                           @Param("pageSize") Integer pageSize,
                           @Param("userId") Integer userId);
}
