package com.scut.server.util;

import java.util.Map;
import java.util.TreeMap;

public class JsonHelper {

    private Map<String,Object> resultMap = new TreeMap<>();

    public JsonHelper put(String key, Object value){
        this.resultMap.put(key, value);
        return this;
    }


    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public JsonHelper() {
    }

    public static JsonHelper getRuseltSet(){
        return new JsonHelper();
    }
}
