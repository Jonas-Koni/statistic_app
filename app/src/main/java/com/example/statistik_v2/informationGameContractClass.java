package com.example.statistik_v2;

import android.provider.BaseColumns;

public final class informationGameContractClass {
    public informationGameContractClass() {}

    public static abstract class StatisticTable implements BaseColumns {
        public static final String TABLE_NAME = "Statistik"; // + db?
        public static final String COLUMN_GAMETYPE = "Game";
        public static final String COLUMN_DATE = "date";
        public static final String ID = "ID";
        public static final String COLUMN_PLAYER_NAME = "name of player";

        //Platzierung:
        public static final String COLUMN_PLACEMENT = "Placement";

        //Kniffel:
        public static final String COLUMN_KNIFFEL_GESAMTSUMME_01 = "total upper section";
        public static final String COLUMN_KNIFFEL_GESAMTSUMME_02 = "total downer section";
        public static final String COLUMN_KNIFFEL_GESAMTSUMME = "total";

        //Mädn
        public static final String COLUMN_MADN_WUERFE = "throw";
        public static final String COLUMN_MADN_GEWORFEN = "thrown";

        //Monopoly
        public static final String COLUMN_MONOPOLY_GELD_BAR = "cash (only winner)";
        public static final String COLUMN_MONOPOLY_GELD_HAUS = "value of house (only winner)";
        public static final String COLUMN_MONOPOLY_GELD_GRUNDSTUECK = "value of property (only winner)";


    }

}
