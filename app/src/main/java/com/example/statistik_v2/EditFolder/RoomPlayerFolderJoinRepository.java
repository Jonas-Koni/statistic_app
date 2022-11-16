package com.example.statistik_v2.EditFolder;

import android.app.Application;
import android.media.AsyncPlayer;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import com.example.statistik_v2.RoomDatabase;

import java.util.List;

public class RoomPlayerFolderJoinRepository {
    private RoomPlayerFolderJoinDao folderJoinDao;
    private LiveData<List<RoomPlayerFolderJoin>> allFolderPlayerJoins;
    private LiveData<List<Integer>> allPlayersInDirectory;
    private int DirectoryId;

    public RoomPlayerFolderJoinRepository(Application application) {
        RoomDatabase database = RoomDatabase.getInstance(application);
        folderJoinDao = database.folderJoinDao();
        allFolderPlayerJoins = database.folderJoinDao().getAllPlayersInFolders();
        allPlayersInDirectory =folderJoinDao.getAllPlayersInDirectory(DirectoryId);
    }

    public void insert(RoomPlayerFolderJoin playerFolderJoin) {
        new InsertPlayerFolderJoinAsyncTask(folderJoinDao).execute(playerFolderJoin);
    }
    public void delete(RoomPlayerFolderJoin playerFolderJoin) {
        new DeletePlayerFolderJoinAsyncTask(folderJoinDao).execute(playerFolderJoin);
    }
    public LiveData<List<RoomPlayerFolderJoin>> getAllFolderPlayerJoins() {return allFolderPlayerJoins;}



    private static class InsertPlayerFolderJoinAsyncTask extends AsyncTask<RoomPlayerFolderJoin, Void, Void> {
        private RoomPlayerFolderJoinDao playerFolderJoinDao;

        private InsertPlayerFolderJoinAsyncTask(RoomPlayerFolderJoinDao roomPlayerFolderJoinDao) {
            this.playerFolderJoinDao = roomPlayerFolderJoinDao;
        }

        @Override
        protected Void doInBackground(RoomPlayerFolderJoin... roomPlayerFolderJoins) {
            playerFolderJoinDao.insert(roomPlayerFolderJoins[0]);
            return null;
        }
    }

    private static class DeletePlayerFolderJoinAsyncTask extends AsyncTask<RoomPlayerFolderJoin, Void, Void> {
        private RoomPlayerFolderJoinDao playerFolderJoinDao;

        private DeletePlayerFolderJoinAsyncTask(RoomPlayerFolderJoinDao roomPlayerFolderJoinDao) {
            this.playerFolderJoinDao = roomPlayerFolderJoinDao;
        }

        @Override
        protected Void doInBackground(RoomPlayerFolderJoin... roomPlayerFolderJoins) {
            playerFolderJoinDao.delete(roomPlayerFolderJoins[0]);
            return null;
        }
    }

    public LiveData<List<Integer>> getAllPlayersInDirectory(int DirectoryId){
        return allPlayersInDirectory;
    }

}
