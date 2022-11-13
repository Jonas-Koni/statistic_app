package com.example.statistik_v2.PlayerListPackage;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.statistik_v2.RoomDatabase;

import java.util.List;

public class RoomPlayerRepository {
    private RoomPlayerDao playerDao;
    private LiveData<List<RoomPlayers>> allPlayers;

    public RoomPlayerRepository(Application application) {
        RoomDatabase database = RoomDatabase.getInstance(application);
        playerDao = database.playerDao();
        allPlayers = playerDao.getAllPlayers();
    }

    public void insert(RoomPlayers player) {
        new InsertNoteAsyncTask(playerDao).execute(player);
    }
    public void update(RoomPlayers player) {
        new UpdateNoteAsyncTask(playerDao).execute(player);
    }

    public void delete(RoomPlayers player) {
        new DeleteNoteAsyncTask(playerDao).execute(player);
    }

    public LiveData<List<RoomPlayers>> getAllPlayers() {
        return allPlayers;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<RoomPlayers, Void, Void> {
        private RoomPlayerDao playerDao;

        private InsertNoteAsyncTask(RoomPlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(RoomPlayers... roomPlayers) {
            playerDao.insert(roomPlayers[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<RoomPlayers, Void, Void> {
        private RoomPlayerDao playerDao;

        private UpdateNoteAsyncTask(RoomPlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(RoomPlayers... roomPlayers) {
            playerDao.update(roomPlayers[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<RoomPlayers, Void, Void> {
        private RoomPlayerDao playerDao;

        private DeleteNoteAsyncTask(RoomPlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(RoomPlayers... roomPlayers) {
            playerDao.delete(roomPlayers[0]);
            return null;
        }
    }

}
