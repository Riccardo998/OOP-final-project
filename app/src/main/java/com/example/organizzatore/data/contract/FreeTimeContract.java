package com.example.organizzatore.data.contract;

import android.provider.BaseColumns;

public class FreeTimeContract {

    private FreeTimeContract(){}

    public static final class FreeTimeEntry implements BaseColumns {
        public static final String TABLE_NAME = "FreeTime";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ATTIVITA = "Attivita";
    }
}
