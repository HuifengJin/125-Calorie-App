package com.example.cs125dto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SqliteDTO extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteOpenHelper";

    private static final String TABLE_NAME = "guest_food_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "stringFood";

    public SqliteDTO(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE "+TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COL2+")";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "Add data: Adding"+item+" to "+TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
        {
            return false;
        }
        return true;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public boolean dropData(int eventId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = " ID = "+ String.valueOf(eventId);
        boolean result = db.delete(TABLE_NAME, whereClause, null)>0;
        return result;
        //return true;
    }
}
