package com.example.root.karunamessenger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by root on 05.04.15.
 */
public class MyDB{

    private DBHelper dbHelper;

    private SQLiteDatabase database;

    public final static String TABLE="TechTable"; // name of table

    public final static String ID="_id";
    public final static String TITLE="title";
    public final static String PICTURE="picture";
    public final static String INFO="info";
    /**
     *
     * @param context
     */
    public MyDB(Context context){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public long createRecords(int id, String title, String picture, String info){
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(TITLE, title);
        values.put(PICTURE, picture);
        values.put(INFO, info);
        return database.insert(TABLE, null, values);
    }

    public Cursor selectRecords() {
        String[] cols = new String[] {ID, TITLE, PICTURE, INFO};
        Cursor mCursor = database.query(true, TABLE,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }
    public void deleteRows() {
        database.execSQL("DELETE FROM TechTable");
    }
    public void dropTable() {
        database.execSQL("DROP TABLE IF EXISTS TechTable");
    }
}