package com.send.back.domain.share;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

/**
 * 功能描述：分享
 *
 * @author 董森
 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
 * @since 2017/5/18
 */
public class Share {
    /**
     * id
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     *内容
     */
    private String content;
    /**
     * 摘要
     */
    private String summary;
    /**
     *发表时间
     */
    private String createTime;
    /**
     *用户Id
     */
    private Integer userId;
    /**
     *用户昵称
     */
    private String nickname;
    /**
     *标签
     */
    private String tags;
    /**
     *是否公开，0是，1否
     */
    private Integer canPublic;
    /**
     *是否可以评论
     */
    private Integer canComment;
    /**
     *查看数
     */
    private Integer viewCount;
    /**
     *喜欢数
     */
    private Integer likeCount;
    /**
     *评论数
     */
    private Integer commentCount;
    /**
     *分享数
     */
    private Integer shareCount;
    /**
     *收藏数
     */
    private Integer collectionCount;
    /**
     *点赞数
     */
    private Integer thumbUpCount;
    /**
     *差劲数，可以据此删除分享
     */
    private Integer thumbDownCount;
    /**
     * 列表页展示图片，从内容中获取
     */
    private String img;

    private List<String> tagList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getCanPublic() {
        return canPublic;
    }

    public void setCanPublic(Integer canPublic) {
        this.canPublic = canPublic;
    }

    public Integer getCanComment() {
        return canComment;
    }

    public void setCanComment(Integer canComment) {
        this.canComment = canComment;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    public Integer getThumbUpCount() {
        return thumbUpCount;
    }

    public void setThumbUpCount(Integer thumbUpCount) {
        this.thumbUpCount = thumbUpCount;
    }

    public Integer getThumbDownCount() {
        return thumbDownCount;
    }

    public void setThumbDownCount(Integer thumbDownCount) {
        this.thumbDownCount = thumbDownCount;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
