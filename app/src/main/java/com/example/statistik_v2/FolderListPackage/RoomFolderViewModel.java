package com.example.statistik_v2.FolderListPackage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class RoomFolderViewModel extends AndroidViewModel {
    private RoomFolderRepository repository;
    private LiveData<List<RoomFolders>> allFolders;

    public RoomFolderViewModel(@NonNull Application application) {
        super(application);
        repository = new RoomFolderRepository(application);
        allFolders = repository.getAllFolders();
    }

    public void insert(RoomFolders folders) {
        repository.insert(folders);
    }
    public void update(RoomFolders folders) {
        repository.update(folders);
    }

    public void delete(RoomFolders folders) {
        repository.delete(folders);
    }

    public LiveData<List<RoomFolders>> getAllFolders() {
        return allFolders;
    }
}
