package com.example.statistik_v2.FolderListPackage;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.statistik_v2.RoomDatabase;

import java.util.List;

public class RoomFolderRepository {
    private RoomFolderDao folderDao;
    private LiveData<List<RoomFolders>> allFolders;

    public RoomFolderRepository(Application application) {
        RoomDatabase database = RoomDatabase.getInstance(application);
        folderDao = database.folderDao();
        allFolders = folderDao.getAllFolders();
    }

    public void insert(RoomFolders folders) {
        new InsertNoteAsyncTask(folderDao).execute(folders);
    }
    public void update(RoomFolders folders) {
        new UpdateNoteAsyncTask(folderDao).execute(folders);
    }
    public void delete(RoomFolders folders) {
        new DeleteNoteAsyncTask(folderDao).execute(folders);
    }
    public LiveData<List<RoomFolders>> getAllFolders() {
        return allFolders;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<RoomFolders, Void, Void> {
        private RoomFolderDao folderDao;

        private InsertNoteAsyncTask(RoomFolderDao folderDao) {
            this.folderDao = folderDao;
        }

        @Override
        protected Void doInBackground(RoomFolders... roomFolders) {
            folderDao.insert(roomFolders[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<RoomFolders, Void, Void> {
        private RoomFolderDao folderDao;

        private UpdateNoteAsyncTask(RoomFolderDao folderDao) {
            this.folderDao = folderDao;
        }

        @Override
        protected Void doInBackground(RoomFolders... roomFolders) {
            folderDao.update(roomFolders[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<RoomFolders, Void, Void> {
        private RoomFolderDao folderDao;

        private DeleteNoteAsyncTask(RoomFolderDao folderDao) {
            this.folderDao = folderDao;
        }

        @Override
        protected Void doInBackground(RoomFolders... roomFolders) {
            folderDao.delete(roomFolders[0]);
            return null;
        }
    }



}
