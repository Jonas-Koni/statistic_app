package com.example.statistik_v2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StatisticDataSource {

    public static final String LOG_TAG = StatisticDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private informationGameDbHelper dbHelper;

    public StatisticDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den DbHelper.");
        dbHelper = new informationGameDbHelper(context);
    }

    public void open(){

    }

    public void close(){

    }
}
