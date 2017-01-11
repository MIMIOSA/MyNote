package com.example.mynote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

// 用来填充ListView
public class MyAdapter extends BaseAdapter {

    LayoutInflater inflater;
    ArrayList<SaveData> arrayList;

    public MyAdapter(LayoutInflater inf, ArrayList<SaveData> array) {
        this.inflater = inf;
        this.arrayList = array;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.adapter_listview, null);
            vh.tv1 = (TextView) convertView.findViewById(R.id.textView1);
            vh.tv2 = (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        vh.tv1.setText(arrayList.get(position).getTitle());
        vh.tv2.setText(arrayList.get(position).getTimes());
        return convertView;
    }

    class ViewHolder {
        TextView tv1, tv2;
    }
}
