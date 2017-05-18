package com.send.back.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface ShareMapper {

    /**
     * 功能描述：添加分享
     *
     * @param title
     * @param tags
     * @param userId
     * @param content
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/15
     */
    @Insert({"insert into share_content(user_id,title,content,tags) values(#{userId},#{title},#{content},#{tags})"})
    Integer addShare(@Param("title") String title,
                  @Param("content") String content,
                  @Param("tags") String tags,
                  @Param("userId") Integer userId);
}
