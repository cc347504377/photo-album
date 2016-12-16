package com.example.whr.uploaddemo.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.example.whr.uploaddemo.info.ImageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whr on 16-12-15.
 */

public class ImageUtil {
    private Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private static ContentResolver contentResolver;
    private static ImageUtil imageUtil;
    private List<ImageInfo> datas;
    private final BitmapFactory.Options options;
    private static int displayw;

    private ImageUtil() {
        options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 5;   //width，hight设为原来的十分一
    }
    public static ImageUtil getinstance(ContentResolver contentResolver,Context context) {

        ImageUtil.contentResolver = contentResolver;
        if (imageUtil == null) {
            imageUtil = new ImageUtil();
            displayw = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        }
        return imageUtil;
    }

    public List<ImageInfo> getimages() {
        datas = new ArrayList<>();
        Cursor cursor = contentResolver.query(uri, new String[]{MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.DATA}
                , null, null, null);
        while (cursor.moveToNext()) {
            datas.add(new ImageInfo(cursor.getString(1), cursor.getString(2)));
        }
        cursor.close();
        return datas;
    }

    public void bitmaptransform(final String imagepath, ImageView imageView, Handler handler, final int position) {
        Bitmap bitmap = BitmapFactory.decodeFile(imagepath, options);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        float hw = (float)height/(float)width;
        final ImageView newview = imageviewtransform(imageView, displayw, hw);
        CacheUtil.getinstance().addImageToCache(imagepath, bitmap);
        handler.post(new Runnable() {
            @Override
            public void run() {
                if ((int)newview.getTag()==position)
                newview.setImageBitmap(CacheUtil.getinstance().getImageFromCache(imagepath));
            }
        });
    }

    public ImageView imageviewtransform(ImageView imageView, int displayw, float hw) {
        imageView.getLayoutParams().width = displayw/2;
        imageView.getLayoutParams().height = (int) (displayw/2 * hw);
        return imageView;
    }

}
