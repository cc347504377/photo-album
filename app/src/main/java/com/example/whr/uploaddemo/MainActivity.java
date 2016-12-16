package com.example.whr.uploaddemo;

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
        List<ImageInfo> imageInfos = ImageUtil.getinstance(getContentResolver(), this).getimages();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        myAdapter = new MyAdapter(this, imageInfos);
        recyclerView.setAdapter(myAdapter);

    }

}
