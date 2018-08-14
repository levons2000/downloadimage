package com.example.hp.downloadimage.models;

public class RecyclerModel {
    private String imgUrl;
    private String name;
    public boolean isDownloaded;

    public RecyclerModel(String imgUrl, String name, boolean isDownloaded) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.isDownloaded = isDownloaded;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
