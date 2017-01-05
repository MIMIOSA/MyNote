package com.example.mynote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 昊赟睿 on 2017/1/5.
 */

public class SecondAtivity extends Activity {
    EditText ed1, ed2;
    Button bt1;
    MyDataBase myDatabase;
    SaveData saveData;
    int ids;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second);
        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        bt1 = (Button) findViewById(R.id.button1);
        myDatabase = new MyDataBase(this);

        Intent intent = this.getIntent();
        ids = intent.getIntExtra("ids", 0);
        //默认为0，不为0,则为修改数据时跳转过来的
        if (ids != 0) {
            saveData = myDatabase.getTiandCon(ids);
            ed1.setText(saveData.getTitle());
            ed2.setText(saveData.getContent());
        }
        //保存按钮的点击事件，他和返回按钮是一样的功能，所以都调用isSave()方法；
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isSave();
            }
        });
    }

    // 返回按钮调用的方法。
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        //super.onBackPressed();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String times = formatter.format(curDate);
        String title = ed1.getText().toString();
        String content = ed2.getText().toString();
        //是要修改数据
        if (ids != 0) {
            saveData = new SaveData(title, ids, content, times);
            myDatabase.toUpdate(saveData);
            Intent intent = new Intent(SecondAtivity.this, MainActivity.class);
            startActivity(intent);
            SecondAtivity.this.finish();
        }
        //新建日记
        else {
            if (title.equals("") && content.equals("")) {
                Intent intent = new Intent(SecondAtivity.this, MainActivity.class);
                startActivity(intent);
                SecondAtivity.this.finish();
            } else {
                saveData = new SaveData(title, content, times);
                myDatabase.toInsert(saveData);
                Intent intent = new Intent(SecondAtivity.this, MainActivity.class);
                startActivity(intent);
                SecondAtivity.this.finish();
            }

        }
    }

    private void isSave() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String times = formatter.format(curDate);
        String title = ed1.getText().toString();
        String content = ed2.getText().toString();
        //是要修改数据
        if (ids != 0) {
            saveData = new SaveData(title, ids, content, times);
            myDatabase.toUpdate(saveData);
            Intent intent = new Intent(SecondAtivity.this, MainActivity.class);
            startActivity(intent);
            SecondAtivity.this.finish();
        }
        //新建日记
        else {
            saveData = new SaveData(title, content, times);
            myDatabase.toInsert(saveData);
            Intent intent = new Intent(SecondAtivity.this, MainActivity.class);
            startActivity(intent);
            SecondAtivity.this.finish();
        }
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second_ativity, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "标题："+ed1.getText().toString()+"    内容："+ed2.getText().toString());
                startActivity(intent);
                break;

            default:
                break;
        }
        return false;
    }*/
}
