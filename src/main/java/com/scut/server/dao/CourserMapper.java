package com.scut.server.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourserMapper {

    @Select("select * from courser")
    List<Courser> getAllCouser();

    @Select("select * from courser where courser_no = #{id}")
    Courser getCourserById(@Param("id") String id);
}
