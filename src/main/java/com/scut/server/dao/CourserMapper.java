package com.scut.server.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourserMapper {

    @Select("select * from courser")
    List<Courser> getAllCourser();

    @Select("select * from courser where courser_no = #{id}")
    Courser getCourserById(@Param("id") int id);

    //@Options(useGeneratedKeys = true,keyProperty = "courser_no",keyColumn = "courser_no")
    @Insert("INSERT INTO courser VALUES " +
            "(null," +
            "#{courser.courser_name}," +
            "#{courser.courser_description}," +
            "#{courser.courser_begin_date}," +
            "#{courser.courser_end_date}," +
            "#{courser.courser_status}," +
            "#{courser.courser_teacher_openid}," +
            "0)")
    void insertCourser(@Param("courser") Courser courser);


    @Insert("REPLACE INTO student_courser VALUES (#{openid},#{courser_id})")
    int insertStudentCourser(@Param("courser_id") String courserId, @Param("openid") String openid);


    @Select("SELECT * FROM courser LEFT JOIN (SELECT * FROM student_courser WHERE student_openid = #{openid}) temp" +
            " ON courser.courser_no = temp.courser_id")
    List<StudentCourser> selectStudentCourser(@Param("openid") String openid);


    @Delete("DELETE FROM student_courser WHERE student_openid = #{openid} AND courser_id = #{courser_id}")
    void deleteSelectedCourser(@Param("openid") String openid, @Param("courser_id") String courser_id);

    @Delete("DELETE FROM courser WHERE courser_no = #{courser_id}")
    void deleteCourser(@Param("courser_id") int courser_id);

    @Update("UPDATE courser SET " +
            "courser_name = #{courser.courser_name}," +
            "courser_description = #{courser.courser_description}," +
            "courser_begin_date = #{courser.courser_begin_date}," +
            "courser_end_date = #{courser.courser_end_date}," +
            "courser_status = #{courser.courser_status} " +
            "WHERE courser_no = #{courser.courser_no}")
    void updateCourser(@Param("courser") Courser courser);
}
