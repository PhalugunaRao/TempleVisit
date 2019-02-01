package com.temples.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.temples.R;
import com.temples.model.TempleDetailsData;

import java.util.List;

public class TemplePassAdapter extends BaseAdapter {
    List<TempleDetailsData.TemplePass> data;
    Context mycontext;
    ViewHolder holder = null;

    public TemplePassAdapter(Context mycontext, List<TempleDetailsData.TemplePass> data) {
        this.mycontext = mycontext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater)
                mycontext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.package_data_row, null);
            holder.package_name = convertView.findViewById(R.id.package_name);
            holder.package_include_tax = convertView.findViewById(R.id.package_include_tax);
            holder.package_price = convertView.findViewById(R.id.package_price);
            holder.card_view=convertView.findViewById(R.id.card_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {
            holder.package_name.setText("Package -"+" "+String.valueOf(position+1));
            holder.package_include_tax.setText("Including Tax");
            holder.package_price.setText("$"+" "+data.get(position).getFeeAmount());
            if(data.get(position).getColor().equalsIgnoreCase("orange")){
                holder.card_view.setBackgroundResource(R.drawable.package_one_bg);
            }else  if(data.get(position).getColor().equalsIgnoreCase("blue")){
                holder.card_view.setBackgroundResource(R.drawable.package_two_bg);
            }else  if(data.get(position).getColor().equalsIgnoreCase("red")){
                holder.card_view.setBackgroundResource(R.drawable.package_three_bg);
            }else{
                holder.card_view.setBackgroundResource(R.drawable.package_one_bg);
            }

        } catch (NullPointerException e) {
        }

        return convertView;
    }

    public class ViewHolder {

        TextView package_price;
        TextView package_include_tax;
        TextView package_name;
        CardView card_view;


    }
}
