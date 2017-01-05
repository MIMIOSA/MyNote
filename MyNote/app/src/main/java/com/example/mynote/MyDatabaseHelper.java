package com.example.mynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 昊赟睿 on 2017/1/5.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table mybook("
            + "ids integer PRIMARY KEY autoincrement,"
            + "title text,"
            + "content text,"
            + "times text)";

    public MyDatabaseHelper(Context context) {
        //super(context, name, factory, version);
        super(context, "mydate", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
