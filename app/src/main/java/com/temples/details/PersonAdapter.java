package com.temples.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.temples.R;
import com.temples.adapter.TemplePassAdapter;
import com.temples.model.BookingHIstoryDetailData;
import com.temples.model.TempleDetailsData;

import java.util.List;

public class PersonAdapter extends BaseAdapter {
    List<BookingHIstoryDetailData.PersonData> data;
    Context mycontext;
    ViewHolder holder = null;

    public PersonAdapter(Context mycontext, List<BookingHIstoryDetailData.PersonData> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater)
                mycontext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.person_row, null);
            holder.firstName = convertView.findViewById(R.id.first_name);
            holder.imagePerson = convertView.findViewById(R.id.imgPrv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try {
            holder.firstName.setText(data.get(position).getFirstName()+" "+ data.get(position).getLastName());
            try{
                Glide.with(mycontext).load(data.get(position).getImageFileName())
                        .placeholder(R.drawable.profile).error(R.drawable.profile)
                        .into(holder.imagePerson);
            }catch (Exception e){

            }

        } catch (NullPointerException e) {
        }

        return convertView;
    }

    public class ViewHolder {

        ImageView imagePerson;
        TextView firstName;



    }
}
