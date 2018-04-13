package com.scut.server.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select count(user_openid) from user where user_openid = #{openid}")
    int isReisgered(@Param("openid") String openid);

    @Insert("INSERT INTO user VALUES (#{user.user_openid},#{user.user_id},#{user.user_birthday},#{user.user_sex},#{user.user_name},#{user.user_role})")
    void insertUser(@Param("user") User user);
}
