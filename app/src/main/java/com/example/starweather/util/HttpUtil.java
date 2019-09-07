package com.example.starweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

// 使用OkHttp完成与服务器的交互工作
public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
