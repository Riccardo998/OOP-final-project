package com.example.organizzatore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.organizzatore.data.contract.SportContract.SportEntry;
import com.example.organizzatore.data.contract.LavoroContract.LavoroEntry;
import com.example.organizzatore.data.contract.StudioContract.StudioEntry;
import com.example.organizzatore.data.contract.FreeTimeContract.FreeTimeEntry;



public class AttivitaDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="organizzatore.db";
    private static final int DATABASE_VERSION = 1;
    /*database version: se cambio lo schema del db incremento il database version*/



    public AttivitaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_SPORT_TABLE = "CREATE TABLE " + SportEntry.TABLE_NAME + "("
            + SportEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SportEntry.COLUMN_ATTIVITA + " TEXT NOT NULL);";      /*avrei potuto aggiungere: + SportEntry.COLUMN_COSE_DA_FARE + " TEXT NOT NULL, "
            + SportEntry.COLUMN_REP + " INTEGER NOT NULL DEFAULT 1, "
            + SportEntry.COLUMN_TIME + " INTEGER NOT NULL DEFAULT 0*/
        String SQL_CREATE_LAVORO_TABLE = "CREATE TABLE " + LavoroEntry.TABLE_NAME + "("
                + LavoroEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LavoroEntry.COLUMN_ATTIVITA + " TEXT NOT NULL);";
        String SQL_CREATE_STUDIO_TABLE = "CREATE TABLE " + StudioEntry.TABLE_NAME + "("
                + StudioEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + StudioEntry.COLUMN_ATTIVITA + " TEXT NOT NULL);";
        String SQL_CREATE_FREETIME_TABLE = "CREATE TABLE " + FreeTimeEntry.TABLE_NAME + "("
                + FreeTimeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FreeTimeEntry.COLUMN_ATTIVITA + " TEXT NOT NULL);";


        db.execSQL(SQL_CREATE_SPORT_TABLE);
        db.execSQL(SQL_CREATE_LAVORO_TABLE);
        db.execSQL(SQL_CREATE_STUDIO_TABLE);
        db.execSQL(SQL_CREATE_FREETIME_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SportEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LavoroEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + StudioEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FreeTimeEntry.TABLE_NAME);
        // Crea la tabella di nuovo
        onCreate(db);

    }
    public void deleteSportEntry(int position) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + SportEntry.TABLE_NAME + " WHERE " +
                SportEntry._ID + " = " + position + ";");
        sqLiteDatabase.execSQL("UPDATE " + SportEntry.TABLE_NAME + " SET " + SportEntry._ID + " = " +
                SportEntry._ID + " -1 " + " WHERE " + SportEntry._ID + " > " + position + ";");
    }
    public void deleteLavoroEntry(int position) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + LavoroEntry.TABLE_NAME + " WHERE " +
                LavoroEntry._ID + " = " + position + ";"); /*era j*/
        sqLiteDatabase.execSQL("UPDATE " + LavoroEntry.TABLE_NAME + " SET " + LavoroEntry._ID + " = " +
                LavoroEntry._ID + " -1 " + " WHERE " + LavoroEntry._ID + " > " + position + ";");
    }
    public void deleteStudioEntry(int position) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + StudioEntry.TABLE_NAME + " WHERE " +
                StudioEntry._ID + " = " + position + ";");
        sqLiteDatabase.execSQL("UPDATE " + StudioEntry.TABLE_NAME + " SET " + StudioEntry._ID + " = " +
                StudioEntry._ID + " -1 " + " WHERE " + StudioEntry._ID + " > " + position + ";");
    }
    public void deleteFreeTimeEntry(int position) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + FreeTimeEntry.TABLE_NAME + " WHERE " +
                FreeTimeEntry._ID + " = " + position + ";");
        sqLiteDatabase.execSQL("UPDATE " + FreeTimeEntry.TABLE_NAME + " SET " + FreeTimeEntry._ID + " = " +
                FreeTimeEntry._ID + " -1 " + " WHERE " + FreeTimeEntry._ID + " > " + position + ";");
    }


    public void insertSportDb(String nome){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SportEntry.COLUMN_ATTIVITA, nome);
        /*values.put(SportContract.SportEntry.COLUMN_COSE_DA_FARE, nome);
        values.put(SportContract.SportEntry.COLUMN_TIME, 1);*/
        db.insert(SportEntry.TABLE_NAME,null,values);
    }

    public void insertLavoroDb(String nome){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LavoroEntry.COLUMN_ATTIVITA, nome);
        db.insert(LavoroEntry.TABLE_NAME,null,values);
    }

    public void insertStudioDb(String nome){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudioEntry.COLUMN_ATTIVITA, nome);
        db.insert(StudioEntry.TABLE_NAME,null,values);
    }

    public void insertFreeTimeDb(String nome){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FreeTimeEntry.COLUMN_ATTIVITA, nome);
        db.insert(FreeTimeEntry.TABLE_NAME,null,values);
    }



    public Cursor getAllItemsSport() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                SportEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public Cursor getAllItemsLavoro() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                LavoroEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public Cursor getAllItemsStudio() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                StudioEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public Cursor getAllItemsFreeTime() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                FreeTimeEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }


}
