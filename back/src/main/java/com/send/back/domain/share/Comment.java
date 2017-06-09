package com.send.back.domain.share;

/**
 * 功能描述： 评论
 *
 * @author 董森
 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
 * @since 2017/6/9
 */
public class Comment {

    private Integer id;
    private Integer userId;
    private String avatar;
    private Integer shareId;
    private Integer parentId;
    private String content;
    private String create_time;
    private Integer li;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getLi() {
        return li;
    }

    public void setLi(Integer like) {
        this.li = li;
    }
}
