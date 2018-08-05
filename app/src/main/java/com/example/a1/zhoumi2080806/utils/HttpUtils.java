package com.example.a1.zhoumi2080806.utils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 1 on 2018/8/6.
 */

public class HttpUtils {
    private static OkHttpClient client=null;
    private static final HttpUtils ourInstance = new HttpUtils();

    public static OkHttpClient getInstance() {
        if (client==null){
            synchronized (HttpUtils.class){
                if (client==null){
                    client=new OkHttpClient();
                }
            }
        }
        return client;
    }

    private HttpUtils() {
    }

    public static void doGet(String url, Callback callback) {
        Request request=new Request.Builder().url(url).build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

}
