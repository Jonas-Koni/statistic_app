package com.example.statistik_v2.PlayerListPackage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomPlayerDao {

    @Insert
    void insert(RoomPlayers roomPlayers);

    @Update
    void update(RoomPlayers roomPlayers);

    @Delete
    void delete(RoomPlayers roomPlayers);

    @Query("SELECT * FROM player_table ORDER BY id DESC")
    LiveData<List<RoomPlayers>> getAllPlayers();

}
