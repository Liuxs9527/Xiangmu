package com.example.administrator.xiangmu.data.utils;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OKHttpUtils {

    private static OkHttpClient okHttpClient;
    private static Request request;
    private static RequestBody builder;

    //OKHTTP  GET异步请求方式
    public static void OkHttpGet(String mPath, Callback callBack){
        //创建okHttpClient
        okHttpClient = new OkHttpClient();
        //获取request请求
        request = new Request.Builder().url(mPath).method("GET", null).build();
        //异步请求
        okHttpClient.newCall(request).enqueue(callBack);
    }

    //okhttp post请求
    public static  void OkHttpPost(String url,String name,String pwd,Callback callback){
        okHttpClient = new OkHttpClient();
        builder = new FormBody.Builder().add("phone",name).add("pwd",pwd).build();
        //创建request
        request = new Request.Builder().url(url).post(builder).build();
        okHttpClient.newCall(request).enqueue(callback);
    }


}
