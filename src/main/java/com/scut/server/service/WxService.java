package com.scut.server.service;


import com.scut.server.dao.User;
import com.scut.server.dao.UserMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WxService {


    private OkHttpClient okHttpClient = new OkHttpClient();

    @Autowired
    private UserMapper userMapper;


    public User getUserByopenId(String openid){
        return userMapper.selectUserByOpenid(openid);
    }

    public boolean isNeedRegister(String openid){
        if(userMapper.isReisgered(openid) == 1){
            return true;
        }else{
            return false;
        }
    }

    public void registerUser(User user){
        userMapper.insertUser(user);
    }

    public String getOpenid(String jscode){
        JSONObject jsonObject = wxLogin(jscode);
        String openid = jsonObject.getString("openid");
        return openid;
    }


    public  synchronized  String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    private  synchronized JSONObject wxLogin(String jsCode){
        String appid = "wxf5252a910ca28fa1";
        String secret = "5d5d0849834509f8661fc326a42dde16";
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                + appid + "&secret="+ secret + "&js_code=" + jsCode +"&grant_type=authorization_code";
        String data = "";
        try {
            data = get(url);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new JSONObject(data);
    }
}
