package com.scut.server.controller;

import com.scut.server.dao.Courser;
import com.scut.server.dao.StudentCourser;
import com.scut.server.service.CourserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CourserController {

    @Autowired
    private CourserService courserService;


    @GetMapping("/courser")
    public List<StudentCourser> getAllCourser(@RequestParam String openid){
        return courserService.getAllCouser(openid);
    }

    @GetMapping("/courser/{id}")
    public Courser getCourserById(@PathVariable String id){
        return courserService.getCourserById(id);
    }

    @PutMapping("/courser/{id}")
    public void selectCourser(@PathVariable("id") String courserId , @RequestBody String openidJson){
        String openid = new JSONObject(openidJson).getString("openid");
        courserService.selectCourser(openid, courserId);
    }

    @DeleteMapping("/courser/{id}")
    public void deleteSelectedCourser(@PathVariable("id") String courserId, @RequestBody String jsonData){
        JSONObject data = new JSONObject(jsonData);
        String openid = data.getString("openid");
        courserService.deleteSelectedCourser(openid,courserId);
    }


}
