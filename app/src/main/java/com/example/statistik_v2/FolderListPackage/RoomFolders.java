package com.example.statistik_v2.FolderListPackage;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "folder_table")
public class RoomFolders {

    @PrimaryKey(autoGenerate = true)
    int id;
    int imageResource;
    String GameType;
    String Title;
    String Description;

    public RoomFolders(int imageResource, String gameType, String title, String description) {
        this.imageResource = imageResource;
        this.GameType = gameType;
        this.Title = title;
        this.Description = description;
    }

    public RoomFolders() {

    }

    public int getId() {
        return id;
    }
    public int getImageResource() {return imageResource;}
    public String getGameType() {
        return GameType;
    }
    public String getTitle() {
        return Title;
    }
    public String getDescription() {
        return Description;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setImageResource(int imageResource) {this.imageResource = imageResource;}
    public void setGameType(String gameType) {
        GameType = gameType;
    }
    public void setTitle(String title) {
        Title = title;
    }
    public void setDescription(String description) {
        Description = description;
    }
}
