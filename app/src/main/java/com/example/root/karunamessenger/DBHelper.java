package com.example.root.karunamessenger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by root on 05.04.15.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Technologies.db";

    private static final int DATABASE_VERSION = 3;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table TechTable ( _id integer primary key, title text, picture text, info text);";
    private static final String DROP_ROWS = "delete from TechTable;";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

//    public void onOpen(SQLiteDatabase database) {
//        database.execSQL(DROP_ROWS);
//    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS TechTable");
        onCreate(database);
    }
}
