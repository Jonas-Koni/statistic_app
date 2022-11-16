package com.example.statistik_v2.EditFolder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomPlayerFolderJoinDao {

    @Insert
    void insert(RoomPlayerFolderJoin folderJoin);

    @Delete
    void delete(RoomPlayerFolderJoin folderJoin);

    @Query("SELECT * FROM folder_player_join ORDER BY DirectoryId DESC")
    LiveData<List<RoomPlayerFolderJoin>> getAllPlayersInFolders();

    @Query("SELECT playerId FROM folder_player_join WHERE directoryId =:directoryId")
    LiveData<List<Integer>> getAllPlayersInDirectory(int directoryId);
}
