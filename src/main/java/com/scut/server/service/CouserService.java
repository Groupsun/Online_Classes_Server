package com.scut.server.service;

import com.scut.server.dao.Courser;
import com.scut.server.dao.CourserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouserService {

    @Autowired
    private CourserMapper courserMapper;

    public List<Courser> getAllCouser(){
        return courserMapper.getAllCouser();
    }

    public Courser getCourserById(String id){
        return courserMapper.getCourserById(id);
    }

}
