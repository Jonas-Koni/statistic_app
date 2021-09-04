package com.example.statistik_v2;

import android.database.sqlite.SQLiteDatabase;

public class informationGameDbHandler implements Runnable {
    private informationGameDbHelper dbHelper = null;
    private MainActivity mainActivity = null;
    private String[] sqlInputData = null;
    private String[] sqlOutputData = null;
    private boolean isBusy = false;
    private int currentSqlCommand = -1;
    public static final int SQL_CMD_NEW_USER = 0;

    public informationGameDbHandler (informationGameDbHelper dbHelper, MainActivity mainActivity) {
        this.dbHelper = dbHelper;
        this.mainActivity = mainActivity;
    }

    public synchronized boolean setNewSqlAction(int newSqlCommand, String[] sqlData) {
        if (this.isBusy) {
            return false;
        }
        this.isBusy = true;
        this.currentSqlCommand = newSqlCommand;
        this.sqlInputData = sqlData;
        return true;
    }



    @Override
    public void run() {
        sqlOutputData = null;
        SQLiteDatabase database = this.dbHelper.getWritableDatabase();

        switch (this.currentSqlCommand) {
            case SQL_CMD_NEW_USER:
                //long newUserId = dbHelper.insertDateList();
        }
    }
}
