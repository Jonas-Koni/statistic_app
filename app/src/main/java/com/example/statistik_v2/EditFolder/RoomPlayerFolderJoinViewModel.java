package com.example.statistik_v2.EditFolder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RoomPlayerFolderJoinViewModel extends AndroidViewModel {
    private RoomPlayerFolderJoinRepository repository;
    private LiveData<List<RoomPlayerFolderJoin>> allFolders;

    public RoomPlayerFolderJoinViewModel(@NonNull Application application) {
        super(application);
        repository = new RoomPlayerFolderJoinRepository(application);
        allFolders = repository.getAllFolderPlayerJoins();
    }

    public void insert(RoomPlayerFolderJoin playerFolderJoin) {
        repository.insert(playerFolderJoin);
    }
    public void delete(RoomPlayerFolderJoin playerFolderJoin) {
        repository.delete(playerFolderJoin);
    }
    public LiveData<List<Integer>> getAllPlayersInDirectory(int DirectoryId) {
        return repository.getAllPlayersInDirectory(DirectoryId);

    }

    public LiveData<List<RoomPlayerFolderJoin>> getAllPlayerFolderJoins() {
        return allFolders;
    }

}
