package com.example.organizzatore.data.contract;

import android.provider.BaseColumns;

public class LavoroContract {

    private LavoroContract(){}

    public static final class LavoroEntry implements BaseColumns {
        public static final String TABLE_NAME = "lavoro";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ATTIVITA = "Attivita";
    }
}
