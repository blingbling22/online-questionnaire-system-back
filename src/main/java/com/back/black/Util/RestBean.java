package com.back.black.Util;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

//用于返回 json 的格式
public record RestBean<T>(int code, T data, String message) {
    public String asJSONString(){
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }

    public static <T> RestBean<T> success(T data) {
        return new RestBean<>(200, data, "Success");
    }
    public static <T> RestBean<T> success() {
        return success(null);
    }

    public  static <T> RestBean<T> failure(int code,String message){
        return new RestBean<>(code, null,message);
    }
    public  static <T> RestBean<T> unauthoried(String message){
        return failure(401,message);
    }
    public  static <T> RestBean<T> forbidden(String message){
        return failure(403,message);
    }
}