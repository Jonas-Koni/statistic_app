package com.example.statistik_v2.EditFolder;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.example.statistik_v2.FolderListPackage.RoomFolders;
import com.example.statistik_v2.PlayerListPackage.RoomPlayers;

@Entity(tableName = "folder_player_join",
        primaryKeys = {"playerId", "directoryId"},
        foreignKeys = {
                @ForeignKey(entity = RoomPlayers.class,
                        parentColumns = "id",
                        childColumns = "playerId"),
                @ForeignKey(entity = RoomFolders.class,
                        parentColumns = "id",
                        childColumns = "directoryId")

        })

public class RoomPlayerFolderJoin {
        public final int playerId;
        public final int directoryId;

        public RoomPlayerFolderJoin(final int playerId, final int directoryId) {
                this.playerId = playerId;
                this.directoryId = directoryId;
        }




}
