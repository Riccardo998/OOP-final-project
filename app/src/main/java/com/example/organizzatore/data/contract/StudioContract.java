package com.example.organizzatore.data.contract;

import android.provider.BaseColumns;

public class StudioContract {

    private StudioContract(){}

    public static final class StudioEntry implements BaseColumns {
        public static final String TABLE_NAME = "studio";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ATTIVITA = "Attivita";
    }
}
