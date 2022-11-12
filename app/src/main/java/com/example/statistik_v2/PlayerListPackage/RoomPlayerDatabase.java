package com.example.statistik_v2.PlayerListPackage;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {RoomPlayers.class}, version = 1)
public abstract class RoomPlayerDatabase extends RoomDatabase {

    private static RoomPlayerDatabase instance;

    public abstract RoomPlayerDao playerDao();

    public static synchronized RoomPlayerDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RoomPlayerDatabase.class, "player_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private RoomPlayerDao playerDao;

        private PopulateDbAsyncTask(RoomPlayerDatabase database) {
            playerDao = database.playerDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            playerDao.insert(new RoomPlayers(-1, "Jonas"));
            return null;
        }
    }
}
