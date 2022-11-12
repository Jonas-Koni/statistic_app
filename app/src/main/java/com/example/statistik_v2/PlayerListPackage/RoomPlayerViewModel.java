package com.example.statistik_v2.PlayerListPackage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.statistik_v2.PlayerListPackage.RoomPlayerRepository;
import com.example.statistik_v2.PlayerListPackage.RoomPlayers;

import java.util.List;

public class RoomPlayerViewModel extends AndroidViewModel {
    private RoomPlayerRepository repository;
    private LiveData<List<RoomPlayers>> allPlayers;

    public RoomPlayerViewModel(@NonNull Application application) {
        super(application);
        repository = new RoomPlayerRepository(application);
        allPlayers = repository.getAllPlayers();
    }

    public void insert(RoomPlayers players) {
        repository.insert(players);
    }
    public void update(RoomPlayers players) {
        repository.update(players);
    }

    public void delete(RoomPlayers players) {
        repository.delete(players);
    }

    public LiveData<List<RoomPlayers>> getAllPlayers() {
        return allPlayers;
    }

}
