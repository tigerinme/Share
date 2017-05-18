package com.send.back.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;

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
    @Insert({"insert into share_content(user_id,title,content,tags,summary) values(#{userId},#{title},#{content},#{tags},#{summary})"})
    Integer addShare(@Param("title") String title,
                     @Param("content") String content,
                     @Param("tags") String tags,
                     @Param("userId") Integer userId,
                     @Param("summary")String summary);


    /**
     * 功能描述：不存在则插入标签
     *
     * @param tag
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/18
     */
    @Insert({"INSERT INTO share_tags (tag) SELECT #{tag} FROM DUAL WHERE NOT EXISTS (SELECT tag FROM share_tags WHERE tag LIKE CONCAT('%',#{tag},'%') )"})
    void insertTag(String tag);
}
