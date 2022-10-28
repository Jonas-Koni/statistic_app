package com.example.statistik_v2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerListItem implements Parcelable {
    private int mImageResource;
    private String mName;
    private int mPlayerID;



    public PlayerListItem(){//int imageResource, String name) {
        //mName = name;
        //mImageResource = imageResource;
    }

    protected PlayerListItem(Parcel in) {
        mImageResource = in.readInt();
        mName = in.readString();
        mPlayerID = in.readInt();
    }

    public static final Creator<PlayerListItem> CREATOR = new Creator<PlayerListItem>() {
        @Override
        public PlayerListItem createFromParcel(Parcel in) {
            return new PlayerListItem(in);
        }

        @Override
        public PlayerListItem[] newArray(int size) {
            return new PlayerListItem[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImageResource);
        dest.writeString(mName);
        dest.writeInt(mPlayerID);
    }
}
