package com.send.back.service.impl;

import com.send.back.dao.ShareMapper;
import com.send.back.dao.UserMapper;
import com.send.back.domain.response.Result;
import com.send.back.service.inter.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shareService")
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareMapper shareMapper;
    @Override
    public Integer addShare(String title, String content, String tags, Integer userId) {
        return shareMapper.addShare(title,content,tags,userId);
    }
}
