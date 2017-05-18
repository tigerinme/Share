package com.send.back.service.impl;

import com.send.back.dao.ShareMapper;
import com.send.back.dao.UserMapper;
import com.send.back.domain.response.Result;
import com.send.back.service.inter.ShareService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shareService")
public class ShareServiceImpl implements ShareService {


    @Autowired
    private ShareMapper shareMapper;
    @Override
    public Integer addShare(String title, String content, String tags, Integer userId,String summary) {
//        处理标签
        if(StringUtils.isNotBlank(tags) && tags.contains(",")) {
           String[] tagArr = tags.split(",");
           for(String t:tagArr){
               shareMapper.insertTag(t);
           }
        }else{
            shareMapper.insertTag(tags);
        }
        return shareMapper.addShare(title,content,tags,userId,summary);
    }
}
