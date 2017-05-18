package com.send.back.service.inter;


import com.send.back.domain.response.Result;

public interface ShareService {

    /**
    * 功能描述：添加分享
    * @param title
    * @param content
    * @param tags
    * @param userId
    * @return
    * @author 董森
    * @since 2017/5/15
    * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
    */
    Integer addShare(String title,String content,String tags,Integer userId,String summary);
}
