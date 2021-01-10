package com.example.statistik_v2;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

public class FolderItem {
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
    void changeDate(Date Date) { mDate = Date; }

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



}
