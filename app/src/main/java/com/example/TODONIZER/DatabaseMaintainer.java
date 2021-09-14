package com.example.TODONIZER;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseMaintainer extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "NotesDatabase";

    public DatabaseMaintainer(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE Note ( id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT)";
        sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqlQuery = "DROP TABLE IF EXISTS Note";
        sqLiteDatabase.execSQL(sqlQuery);
        onCreate(sqLiteDatabase);
    }
}
