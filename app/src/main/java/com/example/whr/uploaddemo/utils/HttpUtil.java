package com.example.whr.uploaddemo.utils;

import android.graphics.Bitmap;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/**
 * Created by whr on 16-12-15.
 */

public class HttpUtil {
    private static HttpUtil httpUtil = null;
    private OkHttpClient client;
    private List<Bitmap> datas;

    private HttpUtil() {
        client = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(10, 25, TimeUnit.SECONDS))
                .build();
    }

    public static HttpUtil getinstance() {
        if (httpUtil == null) {
            httpUtil = new HttpUtil();
        }
        return httpUtil;
    }

    private void uploadimg(List<Bitmap> bitmaps) {

    }


}
