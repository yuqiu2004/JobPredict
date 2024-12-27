package com.whu.mapper;

import com.whu.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("select * from user where username=#{userame}")
    User getByUsername(String username);
}
