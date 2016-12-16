package com.example.whr.uploaddemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.whr.uploaddemo.R;
import com.example.whr.uploaddemo.info.ImageInfo;
import com.example.whr.uploaddemo.utils.ImageUtil;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by whr on 16-12-15.
 */

public class MyAdapter extends RecyclerView.Adapter {

    private final LayoutInflater inflater;
    private List<ImageInfo> datas;
    private Context context;
    private Handler handler = new Handler();
    private final ExecutorService service;
    private int state;

    public MyAdapter(Context context, List<ImageInfo> datas) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.datas = datas;
        service = Executors.newCachedThreadPool();
    }

    public void setstate(int state) {
        this.state = state;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.imageitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.imageView.setTag(position);
        service.execute(new Runnable() {
            @Override
            public void run() {
                ImageUtil.getinstance(null, context).bitmaptransform(datas.get(position).getPath(), viewHolder.imageView, handler, position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
         CheckBox checkBox;
         ImageView imageView;
         ViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.cb);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
        }
    }
}
