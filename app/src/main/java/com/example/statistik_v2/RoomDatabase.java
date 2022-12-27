package com.example.statistik_v2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.statistik_v2.EditFolder.RoomPlayerFolderJoin;
import com.example.statistik_v2.EditFolder.RoomPlayerFolderJoinDao;
import com.example.statistik_v2.FolderListPackage.RoomFolderDao;
import com.example.statistik_v2.FolderListPackage.RoomFolders;
import com.example.statistik_v2.PlayerListPackage.RoomPlayerDao;
import com.example.statistik_v2.PlayerListPackage.RoomPlayers;

@Database(entities = {RoomPlayers.class, RoomFolders.class, RoomPlayerFolderJoin.class}, version = 2)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    private static RoomDatabase instance;

    public abstract RoomPlayerDao playerDao();
    public abstract RoomFolderDao folderDao();
    public abstract RoomPlayerFolderJoinDao folderJoinDao();

    public static synchronized RoomDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDatabase.class, "database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;
    }

    private static androidx.room.RoomDatabase.Callback roomCallback = new androidx.room.RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private RoomPlayerDao playerDao;
        private RoomFolderDao folderDao;
        private RoomPlayerFolderJoinDao folderJoinDao;

        private PopulateDbAsyncTask(RoomDatabase database) {
            playerDao = database.playerDao();
            folderDao = database.folderDao();
            folderJoinDao = database.folderJoinDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            playerDao.insert(new RoomPlayers(-1, "Beispielspieler"));
            folderDao.insert(new RoomFolders(R.drawable.ic_ordner_empty, "Kniffel", "Beispielkniffel", "Auto Generated Folder"));
            return null;
        }
    }
}
