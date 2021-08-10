package com.example.statistik_v2;

import android.provider.BaseColumns;

public final class informationGameContractClass {
    public informationGameContractClass() {}

    public static abstract class StatisticTable implements BaseColumns {

        public static final String DATABASE_NAME = "statistic";

        //directory list
        public static final String TABLE_NAME_DIRECTORY = "directory";
        public static final String COLUMN_GAMETYPE = "game";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";

        //playerlist
        public static final String TABLE_NAME_PLAYERS = "players";
        public static final String COLUMN_PLAYER_NAME = "name of player";

        //date
        public static final String TABLE_NAME_DATE = "date";
        public static final String COLUMN_DIRECTORY_ID = "ID of directory";
        public static final String COLUMN_GAME_ID = "ID of game (in directory)";
        public static final String COLUMN_DATE = "date";

        //DIrectoryPlayerList
        public static final String TABLE_NAME_DIRECTORY_PLAYERS = "players in directory";
        //directory Id
        public static final String COLUMN_PLAYER_ID = "ID of player";

        //every GameType
        //directory ID
        //game ID
        //PlayerId

        //Platzierung:
        public static final String TABLE_NAME_PLACEMENT = "placement";
        public static final String COLUMN_PLACEMENT = "Placement";

        //Kniffel:
        public static final String TABLE_NAME_KNIFFEL = "kniffel";
        public static final String COLUMN_KNIFFEL_TOTAL_UPPER_SECTION = "total upper section";
        public static final String COLUMN_KNIFFEL_TOTAL_DOWNER_SECTION = "total downer section";
        public static final String COLUMN_KNIFFEL_TOTAL = "total";

        //Mädn
        public static final String TABLE_NAME_MADN = "madn";
        public static final String COLUMN_MADN_THREW = "threw";
        public static final String COLUMN_MADN_THROWN = "thrown";

        //Monopoly
        public static final String TABLE_NAME_MONOPOLY = "monopoly";
        public static final String COLUMN_MONOPOLY_CASH = "cash (only winner)";
        public static final String COLUMN_MONOPOLY_VALUE_HOUSE = "value of house (only winner)";
        public static final String COLUMN_MONOPOLY_VALUE_PROPERTY = "value of property (only winner)";


    }

}
