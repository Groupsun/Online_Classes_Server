package com.scut.server.service;

import com.scut.server.dao.Courser;
import com.scut.server.dao.CourserMapper;
import com.scut.server.dao.StudentCourser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourserService {

    @Autowired
    private CourserMapper courserMapper;

    public List<StudentCourser> getAllCouser(String openid){
        return courserMapper.selectStudentCourser(openid);
    }

    public Courser getCourserById(String id){
        return courserMapper.getCourserById(id);
    }

    public boolean selectCourser(String openid, String courserId){
        if(courserMapper.insertStudentCourser(courserId, openid) >= 1){
            return true;
        } else {
            return false;
        }
    }


    public void deleteSelectedCourser(String openid, String courserId){
       courserMapper.deleteSelectedCourser(openid, courserId);
    }

}
