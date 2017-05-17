package com.send.back.sqlprovider;

import com.alibaba.fastjson.JSONObject;
import com.send.back.domain.user.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */
public class TestJson {
    public static void main(String[] args) {
        List<UserInfo> l = new ArrayList<>();
        UserInfo  u= new UserInfo();
        u.setAvatar("ds");
        l.add(u);
        System.out.println( JSONObject.toJSONString(l));
    }
}
