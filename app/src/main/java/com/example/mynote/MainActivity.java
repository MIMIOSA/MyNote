package com.example.mynote;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    Button button;
    ListView listView;
    LayoutInflater inflater;
    ArrayList<SaveData> arrayList;
    MyDataBase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView1);
        button = (Button) findViewById(R.id.button1);
        inflater = getLayoutInflater();

        myDataBase = new MyDataBase(this);
        arrayList = myDataBase.getArray();
        MyAdapter adapter = new MyAdapter(inflater, arrayList);
        listView.setAdapter(adapter);

        // 点击listView里面的item,进入到第二个页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), SecondAtivity.class);
                intent.putExtra("ids", arrayList.get(position).getIds());
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        //长按后来判断是否删除数据
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                //AlertDialog,来判断是否删除笔记。
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("删除")
                        .setMessage("是否删除笔记")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDataBase.toDelete(arrayList.get(position).getIds());
                        arrayList = myDataBase.getArray();
                        MyAdapter adapter = new MyAdapter(inflater, arrayList);
                        listView.setAdapter(adapter);
                    }
                }).create().show();
                return true;
            }
        });

        //新建
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondAtivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }
}
