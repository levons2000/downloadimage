package com.example.hp.downloadimage.utils;

import com.example.hp.downloadimage.models.RecyclerModel;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    public static List<RecyclerModel> list = new ArrayList<>();
    public static int position;
    public static RecyclerModel getItemByPos(){
        return list.get(position);
    }
}
