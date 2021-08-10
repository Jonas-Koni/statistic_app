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

    public static final String SQL_CREATE_PLAYERS =
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

    public static final String SQL_CREATE_DIRECTORY_PLAYERS =
            "CREATE TABLE " + informationGameContractClass.StatisticTable.TABLE_NAME_DIRECTORY_PLAYERS +
                    "(" + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " INTEGER, " +
                    informationGameContractClass.StatisticTable.COLUMN_PLAYER_ID + " INTEGER" +
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
        db.execSQL(SQL_CREATE_PLAYERS);
        db.execSQL(SQL_CREATE_DATE);
        db.execSQL(SQL_CREATE_DIRECTORY_PLAYERS);
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

    public void insertDirectoryPlayers(int DirectoryId, int PlayerId) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID, DirectoryId);
        values.put(informationGameContractClass.StatisticTable.COLUMN_PLAYER_ID, PlayerId);
        database.insert(informationGameContractClass.StatisticTable.TABLE_NAME_DIRECTORY_PLAYERS, null, values);
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

    public ArrayList<FolderItem> getDirectoryInformation() {
        ArrayList<FolderItem> folderItemArrayList = new ArrayList<FolderItem>();

        String selectQuery = "SELECT  * FROM " + informationGameContractClass.StatisticTable.TABLE_NAME_DIRECTORY;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                FolderItem folderItem = new FolderItem();
                folderItem.setGameType(cursor.getInt(cursor.getColumnIndex(informationGameContractClass.StatisticTable.COLUMN_GAMETYPE)));
                folderItem.setText1(cursor.getString(cursor.getColumnIndex(informationGameContractClass.StatisticTable.COLUMN_TITLE)));
                folderItem.setText2(cursor.getString(cursor.getColumnIndex(informationGameContractClass.StatisticTable.COLUMN_DESCRIPTION)));

                String selectPlayerQuery = "SELECT  * FROM " + informationGameContractClass.StatisticTable.TABLE_NAME_DIRECTORY_PLAYERS + " WHERE "
                        + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " = " + cursor.getPosition();

                Cursor PlayerCursor = database.rawQuery(selectPlayerQuery, null);

                if (PlayerCursor.moveToFirst()) {
                    ArrayList PlayerList = new ArrayList();
                    do {
                        PlayerList.add(PlayerCursor.getString(PlayerCursor.getColumnIndex(informationGameContractClass.StatisticTable.COLUMN_PLAYER_ID)));
                    } while (PlayerCursor.moveToNext());
                    folderItem.setPlayerList(PlayerList);
                }
                folderItemArrayList.add(folderItem);
            } while (cursor.moveToNext());
        }
        return folderItemArrayList;
    }


    public ArrayList<informationGame> getInformation() { //only game information; PlayerList, title, description, GameType not here
        ArrayList<informationGame> informationGameArrayList = new ArrayList<informationGame>();

        String selectQuery = "SELECT  * FROM " + informationGameContractClass.StatisticTable.TABLE_NAME_DATE;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                informationGame informationGame = new informationGame();
                informationGame.setFolderIndex(cursor.getInt(cursor.getColumnIndex(informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID)));
                informationGame.setGameId(cursor.getInt(cursor.getColumnIndex(informationGameContractClass.StatisticTable.COLUMN_GAME_ID)));
                informationGame.setDate(cursor.getString(cursor.getColumnIndex(informationGameContractClass.StatisticTable.COLUMN_DATE)));

                String selectGameQuery; //0: Platzierung; 1: Kniffel; 2: Mädn; 3: Monopoly; 4: Wikinger Schach; 5: Zeit und Anzahl
                switch (getDirectoryInformation().get(informationGame.getFolderIndex()).getGameType()){ //inefficient
                    case 0:
                        selectGameQuery = "SELECT  * FROM " + informationGameContractClass.StatisticTable.TABLE_NAME_PLACEMENT + " WHERE "
                                + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " = " + cursor.getPosition();
                        Cursor PlacementCursor = database.rawQuery(selectGameQuery, null);
                        if(PlacementCursor.moveToFirst()){
                            informationGame.setRankingPlayers();
                        }
                        break;
                    case 1:
                        selectGameQuery = "SELECT  * FROM " + informationGameContractClass.StatisticTable.TABLE_NAME_KNIFFEL + " WHERE "
                                + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " = " + cursor.getPosition();
                        break;
                    case 2:
                        selectGameQuery = "SELECT  * FROM " + informationGameContractClass.StatisticTable.TABLE_NAME_MADN + " WHERE "
                                + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " = " + cursor.getPosition();
                        break;
                    case 3:
                        selectGameQuery = "SELECT  * FROM " + informationGameContractClass.StatisticTable.TABLE_NAME_MONOPOLY + " WHERE "
                                + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " = " + cursor.getPosition();
                        break;
                    case 4:
                    case 5:
                        //selectPlayerQuery = "SELECT  * FROM " + informationGameContractClass.StatisticTable.TABLE_NAME_KNIFFEL + " WHERE "
                        // + informationGameContractClass.StatisticTable.COLUMN_DIRECTORY_ID + " = " + cursor.getPosition();
                        throw new IllegalStateException("Not implemented yet " + getDirectoryInformation().get(informationGame.getFolderIndex()).getGameType());
                    default:
                        throw new IllegalStateException("Unexpected value: " + getDirectoryInformation().get(informationGame.getFolderIndex()).getGameType());
                }


            } while (cursor.moveToNext());
        }
        return null;
    }

}
