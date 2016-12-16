package com.example.whr.uploaddemo.info;

/**
 * Created by whr on 16-12-15.
 */

public class ImageInfo {
    private String name;
    private String path;

    public ImageInfo(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
