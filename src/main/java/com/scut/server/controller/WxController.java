package com.scut.server.controller;

import com.scut.server.dao.User;
import com.scut.server.service.WxService;
import com.scut.server.util.JsonHelper;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@RestController
public class WxController {

    @Autowired
    WxService wxService;

    @GetMapping("/login")
    public Map<String,Object> login(@RequestParam String jscode){
        JsonHelper jsonHelper = new JsonHelper();
        String openid = wxService.getOpenid(jscode);
        if(wxService.isNeedRegister(openid)){
            jsonHelper.put("result_code",1);
        }else{
            jsonHelper.put("result_code",0);
        }
        jsonHelper.put("openid",openid);
        return jsonHelper.getResultMap();
    }

    @GetMapping("/register")
    public void register(User user){
        wxService.registerUser(user);
    }


    @GetMapping("/user/{openid}")
    public User getUserByOpenid(@PathVariable("openid")String openid){
        return wxService.getUserByopenId(openid);
    }




    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}


