package com.example.statistik_v2.FolderListPackage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.statistik_v2.PlayerListPackage.RoomPlayers;

import java.util.List;

@Dao
public interface RoomFolderDao {

    @Insert
    void insert(RoomFolders folders);

    @Update
    void update(RoomFolders folders);

    @Delete
    void delete(RoomFolders folders);

    @Query("SELECT * FROM folder_table ORDER BY id DESC")
    LiveData<List<RoomFolders>> getAllFolders();
}
