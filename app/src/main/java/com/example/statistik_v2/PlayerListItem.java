package com.example.statistik_v2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

public class PlayerListItem {
    private int mImageResource;
    private String mName;



    public PlayerListItem(int imageResource, String name) {
        mName = name;
        mImageResource = imageResource;
    }

    public int getImageResource(){
        return mImageResource;
    }
    public String getName(){
        return mName;
    }




}
