package com.example.statistik_v2.EditFolder;


public class RoomPlayerFolderLeftJoin {
    int id;
    String name;
    int directoryId;

    public int getPlayerId() {
        return id;
    }
    public String getPlayerName() {
        return name;
    }
    public int getDirectoryId() {
        return directoryId;
    }

    public void setPlayerId(int playerId) {
        this.id = playerId;
    }
    public void setPlayerName(String playerName) {
        this.name = playerName;
    }
    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }
}
