package com.example.statistik_v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class FolderItem implements Parcelable {
    private int mImageResource;
    private String mText1;
    private String mText2;
    private ArrayList mPlayerList = new ArrayList();
    private int mGameType;

    //Zwischenspeicherung bis Übernahme in Datenbank
    private Date mDate;


    FolderItem(int ImageResource, String text1, String text2){
        mImageResource = ImageResource;
        mText1 = text1;
        mText2 = text2;

    }

    protected FolderItem(Parcel in) {
        mImageResource = in.readInt();
        mText1 = in.readString();
        mText2 = in.readString();
        mGameType = in.readInt();
    }

    /*public static final Creator<FolderItem> CREATOR = new Creator<FolderItem>() {
        @Override
        public FolderItem createFromParcel(Parcel in) {
            return new FolderItem(in);
        }

        @Override
        public FolderItem[] newArray(int size) {
            return new FolderItem[size];
        }
    };*/

    void changePlayerList(ArrayList PlayerList){
        mPlayerList = PlayerList;
    }
    void changeGameType(int GameType){
        mGameType = GameType;
    }
    void changeText1(String text){
        mText1 = text;
    }
    void changeText2(String text){
        mText2 = text;
    }
    void changeDate(Date Date) {
        mDate = Date;
    }

    ArrayList getmPlayerList() {return mPlayerList;}
    int getGameType(){return mGameType;}
    int getImageResource(){
        return mImageResource;
    }
    String getText1(){
        return mText1;
    }
    String getText2(){
        return mText2;
    }
    Date getDate() {return mDate;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImageResource);
        dest.writeString(mText1);
        dest.writeString(mText2);
        dest.writeInt(mGameType);
    }
}
