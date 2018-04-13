package com.scut.server.controller;

import com.scut.server.dao.Courser;
import com.scut.server.service.CouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class CourserController {

    @Autowired
    private CouserService couserService;


    @GetMapping("/courser")
    public List<Courser> getAllCourser(){
        return couserService.getAllCouser();
    }

    @GetMapping("/courser/{id}")
    public Courser getCourserById(@PathVariable String id){
        return couserService.getCourserById(id);
    }



}
