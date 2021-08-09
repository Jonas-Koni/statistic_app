package com.example.statistik_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class informationGameDbHelper extends SQLiteOpenHelper{



    public static final int DB_VERSION = 1;
    public static final String DB_DIRECTORY_NAME = "statistic_directory.mydb"; //"com.example.mydb";

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final String SQL_CREATE_DIRECTORY =
            "CREATE TABLE " + informationGameContractClass.StatisticTable.TABLE_NAME_DIRECTORY +
                    "(" + informationGameContractClass.StatisticTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    informationGameContractClass.StatisticTable.COLUMN_GAMETYPE + " TEXT, " +
                    informationGameContractClass.StatisticTable.COLUMN_TITLE + " TEXT, " +
                    informationGameContractClass.StatisticTable.COLUMN_DESCRIPTION + " TEXT" +
                    ");";

    public static final String SQL_CREATE_PLAYERLIST =
            "CREATE TABLE " + informationGameContractClass.StatisticTable.TABLE_NAME_PLAYERS +
                    "(" + informationGameContractClass.StatisticTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    informationGameContractClass.StatisticTable.COLUMN_PLAYER_NAME + " TEXT" +
                    ");";

    public static final String SQL_CREATE_DATE =
            "CREATE TABLE " + informationGameContractClass.StatisticTable.TABLE_NAME_DATE +
                    "(" + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_GAME_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_DATE + " TEXT" +
                    ");";

    public static final String SQL_CREATE_PLACEMENT =
            "CREATE TABLE " + informationGameContractClass.StatisticTable.TABLE_NAME_PLACEMENT +
                    "(" + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_GAME_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_PLAYER_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_PLACEMENT + " INTEGER" +
                    ");";

    public static final String SQL_CREATE_KNIFFEL =
            "CREATE TABLE " + informationGameContractClass.StatisticTable.TABLE_NAME_KNIFFEL +
                    "(" + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_GAME_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_PLAYER_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_KNIFFEL_TOTAL_UPPER_SECTION + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_KNIFFEL_TOTAL_DOWNER_SECTION + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_KNIFFEL_TOTAL + " INTEGER" +
                    ");";

    public static final String SQL_CREATE_MADN =
            "CREATE TABLE " + informationGameContractClass.StatisticTable.TABLE_NAME_MADN +
                    "(" + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_GAME_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_PLAYER_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_MADN_THREW + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_MADN_THROWN + " INTEGER" +
                    ");";

    public static final String SQL_CREATE_MONOPOLY =
            "CREATE TABLE " + informationGameContractClass.StatisticTable.TABLE_NAME_MONOPOLY +
                    "(" + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_GAME_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_PLAYER_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_MONOPOLY_CASH + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_MONOPOLY_VALUE_HOUSE + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_MONOPOLY_VALUE_PROPERTY + " INTEGER" +
                    ");";






    public informationGameDbHelper(Context context) {
        super(context, DB_DIRECTORY_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt");
    }

    public informationGameDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE IF EXISTS " + informationGameContractClass.StatisticTable.DATABASE_NAME);
        db.execSQL(SQL_CREATE_DIRECTORY);
        db.execSQL(SQL_CREATE_PLAYERLIST);
        db.execSQL(SQL_CREATE_DATE);
        db.execSQL(SQL_CREATE_PLACEMENT);
        db.execSQL(SQL_CREATE_KNIFFEL);
        db.execSQL(SQL_CREATE_MADN);
        db.execSQL(SQL_CREATE_MONOPOLY);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertDirectory(String GameType, String title, String description) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(informationGameContractClass.StatisticTable.COLUMN_GAMETYPE, GameType);
        values.put(informationGameContractClass.StatisticTable.COLUMN_TITLE, title);
        values.put(informationGameContractClass.StatisticTable.COLUMN_DESCRIPTION, description);
        database.insert(informationGameContractClass.StatisticTable.TABLE_NAME_DIRECTORY, null, values);
    }

    public void insertPlayerList(String PlayerName) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(informationGameContractClass.StatisticTable.COLUMN_PLAYER_NAME, PlayerName);
        database.insert(informationGameContractClass.StatisticTable.TABLE_NAME_PLAYERS, null, values);
    }

    public void insertDateList(int DirectoryId, int GameId, String Date){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID, DirectoryId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_GAME_ID, GameId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_DATE, Date);
        database.insert(informationGameContractClass.StatisticTable.TABLE_NAME_DATE, null, values);
    }

    public void insertPlacementList(int DirectoryId, int GameId, int PlayerId, int Placement) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID, DirectoryId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_GAME_ID, GameId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_PLAYER_ID, PlayerId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_PLACEMENT, Placement);
        database.insert(informationGameContractClass.StatisticTable.TABLE_NAME_PLACEMENT, null, values);
    }

    public void insertKniffelList(int DirectoryId, int GameId, int PlayerId, int Placement, int TotalUpperSection, int TotalDownerSection, int Total) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID, DirectoryId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_GAME_ID, GameId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_PLAYER_ID, PlayerId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_PLACEMENT, Placement);
        values.put(informationGameContractClass.StatisticTable.COLUMN_KNIFFEL_TOTAL_UPPER_SECTION, TotalUpperSection);
        values.put(informationGameContractClass.StatisticTable.COLUMN_KNIFFEL_TOTAL_DOWNER_SECTION, TotalDownerSection);
        values.put(informationGameContractClass.StatisticTable.COLUMN_KNIFFEL_TOTAL, Total);
        database.insert(informationGameContractClass.StatisticTable.TABLE_NAME_KNIFFEL, null, values);
    }

    public void insertMadnList(int DirectoryId, int GameId, int PlayerId, int Placement, int threw, int thrown) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID, DirectoryId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_GAME_ID, GameId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_PLAYER_ID, PlayerId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_PLACEMENT, Placement);
        values.put(informationGameContractClass.StatisticTable.COLUMN_MADN_THREW, threw);
        values.put(informationGameContractClass.StatisticTable.COLUMN_MADN_THROWN, thrown);
        database.insert(informationGameContractClass.StatisticTable.TABLE_NAME_MADN, null, values);
    }

    public void insertMonopolyList(int DirectoryId, int GameId, int PlayerId, int Placement, int Cash, int ValueOfHouse, int ValueOfProperty) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID, DirectoryId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_GAME_ID, GameId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_PLAYER_ID, PlayerId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_PLACEMENT, Placement);
        values.put(informationGameContractClass.StatisticTable.COLUMN_MONOPOLY_CASH, Cash);
        values.put(informationGameContractClass.StatisticTable.COLUMN_MONOPOLY_VALUE_HOUSE, ValueOfHouse);
        values.put(informationGameContractClass.StatisticTable.COLUMN_MONOPOLY_VALUE_PROPERTY, ValueOfProperty);
        database.insert(informationGameContractClass.StatisticTable.TABLE_NAME_MONOPOLY, null, values);
    }


    public ArrayList<informationGame> getInformation() { //only game information; PlayerList, title, description, GameType not here
        ArrayList<informationGame> informationGameArrayList = new ArrayList<informationGame>();

        String selectQuery = "SELECT  * FROM " + informationGameContractClass.StatisticTable.TABLE_NAME_DATE;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                informationGame informationGame = new informationGame();
                informationGame.s
            }
        }
    }

}
