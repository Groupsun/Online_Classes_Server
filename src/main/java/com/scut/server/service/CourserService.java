package com.scut.server.service;

import com.scut.server.dao.Courser;
import com.scut.server.dao.CourserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;

import java.util.List;

@Service
public class CourserService {

    @Autowired
    private CourserMapper courserMapper;

    public List<Courser> getAllCourser(){
        return courserMapper.getAllCourser();
    }

    public Courser getCourserById(int id){
        return courserMapper.getCourserById(id);
    }


    public void createCourser(Courser courser){
        Date currentDate = new Date();
        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentDate);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime = formatter.parse(dateString, pos);*/

        if(courser.getCourser_begin_date().compareTo(currentDate)<=0){
            courser.setCourser_status(1);
        }
        else{
            courser.setCourser_status(0);
        }
        //System.out.println("comp="+tmp);
        courserMapper.insertCourser(courser);
    }

    public boolean deleteCourser(int courser_id,String tea_openid){
        String courser_teaId=getCourserById(courser_id).getCourser_teacher_openid();
        if(courser_teaId.equals("")||!courser_teaId.equals(tea_openid))
            return false;//没有权限删除别人的课程
        //有权限删除自己的课程
        courserMapper.deleteCourser(courser_id);
        return true;
    }

    public boolean updateCourser(String tea_id,Courser courser){
        String courser_teaId=getCourserById(courser.getCourser_no()).getCourser_teacher_openid();

        if(courser_teaId.equals("")||!courser_teaId.equals(tea_id))
            return false;//没有权限更改别人的课程

        //有权限更改自己的课程
        Date currentDate = new Date();
        if(courser.getCourser_begin_date().compareTo(currentDate)<=0){
            courser.setCourser_status(1);
        }
        else{
            courser.setCourser_status(0);
        }
        courserMapper.updateCourser(courser);
        return true;
    }
}