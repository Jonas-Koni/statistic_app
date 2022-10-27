package com.example.statistik_v2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

public class PlayerListItem {
    private int mImageResource;
    private String mName;
    private int mPlayerID;



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
    public int getmPlayerID() {
        return mPlayerID;
    }

    public void setPlayerID(int playerID) {
        mPlayerID = playerID;
    }
    public void setmName(String name) {
        mName = name;
    }
    public void setmImageResource(int imageResource) {mImageResource = imageResource;}




}
