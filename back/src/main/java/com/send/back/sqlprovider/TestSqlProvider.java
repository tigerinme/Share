package com.send.back.sqlprovider;


import org.springframework.stereotype.Service;


public class TestSqlProvider {

    public  String getUserById (Integer id){
        return "select * from user where id = #{id}";
    }
}
