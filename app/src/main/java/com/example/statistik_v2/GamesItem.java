package com.example.statistik_v2;

public class GamesItem {
    private String mGameName;
    private int mGameIcon;

    public GamesItem(String gamesName, int gameIcon){
        mGameIcon = gameIcon;
        mGameName = gamesName;
    }

    public String getGameName(){return mGameName;}
    public int getGameIcon(){return mGameIcon;}

}
