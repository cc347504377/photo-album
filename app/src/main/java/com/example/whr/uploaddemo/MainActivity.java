package com.example.whr.uploaddemo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.example.whr.uploaddemo.adapter.MyAdapter;
import com.example.whr.uploaddemo.info.ImageInfo;
import com.example.whr.uploaddemo.utils.ImageUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermissionRW(this);
        List<ImageInfo> imageInfos = ImageUtil.getinstance(getContentResolver(), this).getimages();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        myAdapter = new MyAdapter(this, imageInfos);
        recyclerView.setAdapter(myAdapter);

    }

    /**
     * 申请权限
     */
    //声明常量权限
    private final static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
    };
    public static void getPermissionRW(Activity activity) {
        // 是否添加权限
        int permissionW = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRecord = ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);
        //如果没有权限,申请权限
        if (permissionW != PackageManager.PERMISSION_GRANTED||permissionRecord!=PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity, PERMISSIONS_STORAGE, 11
            );
        }
    }
}
