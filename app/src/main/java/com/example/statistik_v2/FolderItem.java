package com.example.statistik_v2;

import android.widget.EditText;

import java.util.ArrayList;

public class FolderItem {
    private int mImageResource;
    private int mVisibility;
    private String mText1;
    private String mText2;
    private String mEditText1;
    private String mEditText2;
    private EditText textEditText1;
    private ArrayList mPlayerList = new ArrayList();
    private int mGameType;

    public FolderItem(int ImageResource, String text1, String text2){
        mImageResource = ImageResource;
        mText1 = text1;
        mText2 = text2;

    }
    public void changePlayerList(ArrayList PlayerList){
        mPlayerList = PlayerList;
    }
    public void changeGameType(int GameType){
        mGameType = GameType;
    }
    public void changeText1(String text){
        mText1 = text;
    }
    public void changeText2(String text){
        mText2 = text;
    }

    public int getImageResource(){
        return mImageResource;
    }
    public String getText1(){
        return mText1;
    }
    public String getText2(){
        return mText2;
    }
    public ArrayList getmPlayerList(){return mPlayerList;}
    public int getGameType(){return mGameType;}


}
