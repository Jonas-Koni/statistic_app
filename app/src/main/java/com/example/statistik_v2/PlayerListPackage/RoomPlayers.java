package com.example.statistik_v2.PlayerListPackage;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player_table")
public class RoomPlayers {

    @PrimaryKey(autoGenerate = true)
    int id;
    int icon; //-1 hash name to color + first letters name as Icon
    String name;

    public int getId() {
        return id;
    }
    public int getIcon() {
        return icon;
    }
    public String getName() {
        return name;
    }

    public RoomPlayers(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String newName) {this.name = newName;}
}
