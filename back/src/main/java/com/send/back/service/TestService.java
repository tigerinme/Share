package com.send.back.service;

import com.send.back.domain.User;
import com.send.back.dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/25.
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public User getUserById(Integer id){
       return testMapper.getUserById(id);
    }
}
