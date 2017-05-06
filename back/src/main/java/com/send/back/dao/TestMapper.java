package com.send.back.dao;

import com.send.back.domain.User;
import com.send.back.sqlprovider.TestSqlProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;



public interface TestMapper {

    @SelectProvider(type = TestSqlProvider.class, method = "getUserById")
//    @Select("select * from user where id = #{id}")
    User getUserById(Integer id);

}
