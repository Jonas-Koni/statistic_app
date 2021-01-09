package com.example.statistik_v2;

public class DataItem {
    private int mImageResource;
    private String mTitle;

    public DataItem(int imageResource, String title){
        mImageResource = imageResource;
        mTitle = title;
    }

    public int getImageResource(){
        return mImageResource;
    }
    public String getTitle(){
        return mTitle;
    }
}
