package com.example.mynote;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by 昊赟睿 on 2017/1/5.
 */

public class MyDataBase {
    Context context;
    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase myDatabase;

    public MyDataBase(Context con) {
        this.context = con;
        myDatabaseHelper = new MyDatabaseHelper(context);
    }

    //得到第一个界面的ListView的array数据
    public ArrayList<SaveData> getArray() {
        ArrayList<SaveData> array = new ArrayList<SaveData>();
        ArrayList<SaveData> array1 = new ArrayList<SaveData>();
        myDatabase = myDatabaseHelper.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("select ids,title,times from mybook", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("ids"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String times = cursor.getString(cursor.getColumnIndex("times"));
            SaveData cun = new SaveData(id, title, times);
            array.add(cun);
            cursor.moveToNext();
        }
        myDatabase.close();
        for (int i = array.size(); i > 0; i--) {
            array1.add(array.get(i - 1));
        }
        return array1;
    }

    //返回可能要修改的数据
    public SaveData getTiandCon(int id) {
        myDatabase = myDatabaseHelper.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("select title,content from mybook where ids='" + id + "'", null);
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        SaveData cun = new SaveData(title, content);
        myDatabase.close();
        return cun;
    }

    //修改
    public void toUpdate(SaveData cun) {
        myDatabase = myDatabaseHelper.getWritableDatabase();
        myDatabase.execSQL("update mybook set title='" + cun.getTitle() + "',times='" + cun.getTimes() + "',content='" + cun.getContent() + "' where ids='" + cun.getIds() + "'");
        myDatabase.close();
    }

    //添加
    public void toInsert(SaveData cun) {
        myDatabase = myDatabaseHelper.getWritableDatabase();
        myDatabase.execSQL("insert into mybook(title,content,times)values('" + cun.getTitle() + "','" + cun.getContent() + "','" + cun.getTimes() + "')");
        myDatabase.close();
    }

    //删除
    public void toDelete(int ids){
        myDatabase=myDatabaseHelper.getWritableDatabase();
        myDatabase.execSQL("delete  from mybook where ids="+ids+"");
        myDatabase.close();
    }
}
