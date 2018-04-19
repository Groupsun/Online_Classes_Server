package com.scut.server.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourserMapper {

    @Select("select * from courser")
    List<Courser> getAllCouser();

    @Select("select * from courser where courser_no = #{id}")
    Courser getCourserById(@Param("id") String id);


    @Insert("REPLACE INTO student_courser VALUES (#{openid},#{courser_id})")
    int insertStudentCourser(@Param("courser_id") String courserId, @Param("openid") String openid);


    @Select("SELECT * FROM courser LEFT JOIN (SELECT * FROM student_courser WHERE student_openid = #{openid}) temp" +
            " ON courser.courser_no = temp.courser_id")
    List<StudentCourser> selectStudentCourser(@Param("openid") String openid);


    @Delete("DELETE FROM student_courser WHERE student_openid = #{openid} AND courser_id = #{courser_id}")
    void deleteSelectedCourser(@Param("openid") String openid, @Param("courser_id") String courser_id);

}
