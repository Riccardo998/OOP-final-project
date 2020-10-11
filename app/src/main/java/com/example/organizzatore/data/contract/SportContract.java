package com.example.organizzatore.data.contract;

import android.provider.BaseColumns;

public final class SportContract {

    private SportContract(){}

    public static final class SportEntry implements BaseColumns {
        public static final String TABLE_NAME = "sport";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ATTIVITA = "Attivita";
        //public static final String COLUMN_COSE_DA_FARE = "cose_da_fare";
        //public static final String COLUMN_REP = "ripetizioni";
        //public static final String COLUMN_TIME = "time_ss";
    }
}
