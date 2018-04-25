package com.scut.server.controller;

import com.scut.server.dao.Courser;
import com.scut.server.service.CourserService;
import com.scut.server.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Map;

@RestController

public class CourserController {

    @Autowired
    private CourserService courserService;


    @GetMapping("/courser")
    public List<Courser> getAllCourser(){
        return courserService.getAllCourser();
    }

    @GetMapping("/courser/{id}")
    public Courser getCourserById(@PathVariable int id){
        return courserService.getCourserById(id);
    }

    @GetMapping("/courser/create")
    public void createCourser(Courser courser){
        courserService.createCourser(courser);
    }

    @GetMapping("/courser/delete/{id}")
    public Map<String,Object> deleteCourser(@PathVariable int id, @RequestParam("courser_teacher_openid") String tea_openid){
        JsonHelper jsonHelper = new JsonHelper();
        if(courserService.deleteCourser(id,tea_openid)){
            jsonHelper.put("result_code",1);
        }
        else{
            jsonHelper.put("result_code",0);
        }
        return jsonHelper.getResultMap();
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
