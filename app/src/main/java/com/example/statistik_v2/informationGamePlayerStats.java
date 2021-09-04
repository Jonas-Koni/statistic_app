package com.example.statistik_v2;

import java.util.ArrayList;

public class informationGamePlayerStats {

    private int Placement;
    private int KniffelGesamtsumme01;
    private int KniffelGesamtsumme02;
    private int MadnWuerfe;
    private int MadnGeworfen;
    private int MonopolyGeldBar;
    private int MonopolyGeldHaus;
    private int MonopolyGeldGrundstueck;
    private int FolderIndex;
    private int GameId;

    public informationGamePlayerStats(int folderIndex, int gameId) {
        FolderIndex = folderIndex;
        GameId = gameId;
    }

    int getFolderIndex() {return FolderIndex;}
    int getGameId() {return GameId;}
    int getKniffelGesamtsumme01() {return KniffelGesamtsumme01;}
    int getKniffelGesamtsumme02() {return KniffelGesamtsumme02;}
    int getRankingPlayers() {return Placement;}
    int getMadnWuerfe() {return MadnWuerfe;}
    int getMadnGeworfen() {return MadnGeworfen;}
    int getMonopolyGeldBar() {return MonopolyGeldBar;}
    int getMonopolyGeldHaus() {return MonopolyGeldHaus;}
    int getMonopolyGeldGrundstueck() {return MonopolyGeldGrundstueck;}

    void setGameId(int gameId) {GameId = gameId;}
    void setFolderIndex(int folderIndex) { FolderIndex = folderIndex; }
    void setRankingPlayers(int placement) {
        Placement = placement;
    }
    void setKniffelTotalUpperSection(int kniffelGesamtsumme01) {
        KniffelGesamtsumme01 = kniffelGesamtsumme01;
    }
    void setKniffelTotalDownerSection(int kniffelGesamtsumme02) {
        KniffelGesamtsumme02 = kniffelGesamtsumme02;
    }
    void setMadnThrew(int madnWuerfe) {
        MadnWuerfe = madnWuerfe;
    }
    void setMadnThrown(int madnGeworfen) {
        MadnGeworfen = madnGeworfen;
    }
    void setMonopolyCash(int geldBar) {MonopolyGeldBar = geldBar;}
    void setMonopolyValueHouse(int geldHaus) {MonopolyGeldHaus = geldHaus;}
    void setMonopolyValueProperty(int geldGrundstueck) {MonopolyGeldGrundstueck = geldGrundstueck;}

}
