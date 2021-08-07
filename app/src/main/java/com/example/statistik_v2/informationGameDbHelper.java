package com.example.statistik_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class informationGameDbHelper extends SQLiteOpenHelper{

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String DB_NAME = "Statistik"; // + db?
    public static final int DB_VERSION = 1;

    public static ArrayList SQL_CREATE = new ArrayList();

    /*public static final String SQL_CREATE =
            "CREATE TABLE " + DB_NAME +
                    "(" + informationGameContractClass.StatisticTable.COLUMN_DATE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    informationGameContractClass.StatisticTable.COLUMN_GAMETYPE + " TEXT" +
                    ");";*/




    public informationGameDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt");
    }

    public informationGameDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit dem Befehl: " + SQL_CREATE + " angelegt.");
            //Daten einlesen fehlt noch
            //0: Platzierung; 1: Kniffel; 2: Mädn; 3: Monopoly; 4: Wikinger Schach; 5: Zeit und Anzahl

            String SQL_CREATE_SRING = SQL_CREATE_STRING();
            db.execSQL(SQL_CREATE_SRING);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }


    public static String getSingleGame(SQLiteDatabase database, int userId) {
        String username = null;
        final String SQL


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String SQL_CREATE_STRING() {
        String SQL_CREATE_STRING =
    }




    /*public String SQL_CREATE_STRING () {
        String SQL_CREATE_SRING =
                "CREATE TABLE " + DB_NAME +
                        "(" + informationGameContractClass.StatisticTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        informationGameContractClass.StatisticTable.COLUMN_DATE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        informationGameContractClass.StatisticTable.COLUMN_GAMETYPE + " TEXT, " +
                        informationGameContractClass.StatisticTable.COLUMN_PLAYER_NAME + " TEXT, ";
        ArrayList<FolderItem> mFolderList = new ArrayList<FolderItem>();

        switch (mFolderList.get(0).getGameType()){
            case 0:
                SQL_CREATE_SRING +=
                        informationGameContractClass.StatisticTable.COLUMN_PLACEMENT + " INTEGER PRIMARY KEY AUTOINCREMENT);";
                break;
            case 1:
                SQL_CREATE_SRING +=
                        informationGameContractClass.StatisticTable.COLUMN_PLACEMENT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                informationGameContractClass.StatisticTable.COLUMN_KNIFFEL_GESAMTSUMME_01 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                informationGameContractClass.StatisticTable.COLUMN_KNIFFEL_GESAMTSUMME_02 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                informationGameContractClass.StatisticTable.COLUMN_KNIFFEL_GESAMTSUMME + " INTEGER PRIMARY KEY AUTOINCREMENT)";
                break;
            case 2:
                SQL_CREATE_SRING +=
                        informationGameContractClass.StatisticTable.COLUMN_PLACEMENT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                informationGameContractClass.StatisticTable.COLUMN_MADN_WUERFE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                informationGameContractClass.StatisticTable.COLUMN_MADN_GEWORFEN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                informationGameContractClass.StatisticTable.COLUMN_KNIFFEL_GESAMTSUMME + " INTEGER PRIMARY KEY AUTOINCREMENT)";
                break;
            case 3:
                SQL_CREATE_SRING +=
                        informationGameContractClass.StatisticTable.COLUMN_PLACEMENT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                informationGameContractClass.StatisticTable.COLUMN_MONOPOLY_GELD_BAR + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                informationGameContractClass.StatisticTable.COLUMN_MONOPOLY_GELD_HAUS + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                informationGameContractClass.StatisticTable.COLUMN_MONOPOLY_GELD_GRUNDSTUECK + " INTEGER PRIMARY KEY AUTOINCREMENT)";

                break;
        }
        return SQL_CREATE_SRING;
    }*/
}
