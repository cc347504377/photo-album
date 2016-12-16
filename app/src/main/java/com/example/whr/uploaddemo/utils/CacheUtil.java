package com.example.whr.uploaddemo.utils;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

/**
 * Created by whr on 16-12-16.
 */

public class CacheUtil {
    private LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(1024 * 1024 * 20){
        /**
         * 此方法用于计算图片大小，不同SDK计算方法不同，
         * 必须重新该方法
         * @param key
         * @param bitmap
         * @return
         */
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                return bitmap.getByteCount();
            }
            // Pre HC-MR1
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    };
    private static CacheUtil cacheUtil = null;

    private CacheUtil() {

    }

    public static CacheUtil getinstance() {
        if (cacheUtil == null) {
            cacheUtil = new CacheUtil();
        }
        return cacheUtil;
    }

    public void addImageToCache(String path, Bitmap bitmap) {
        lruCache.put(path, bitmap);
    }

    public Bitmap getImageFromCache(String path) {
        return  lruCache.get(path);
    }
}
