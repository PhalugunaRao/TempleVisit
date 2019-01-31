package com.temples.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.temples.R;

import java.util.List;

public class SimplePagerGallery extends PagerAdapter {
    Context context;
    List<String> images;
    LayoutInflater mLayoutInflater;


    public SimplePagerGallery(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup collection, final int position)
    {


        ViewGroup itemView;
        itemView = (ViewGroup) mLayoutInflater.inflate(R.layout.gallery_row, collection, false);
        ImageView galleryLogos = itemView.findViewById(R.id.gallery_image);

        if (images.get(position) != null)
            Glide.with(context).load(images.get(position))
                    .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                    .fitCenter()
                    .into(  galleryLogos);

        collection.addView(itemView);
        return itemView;
    }




    @Override
    public void destroyItem(ViewGroup collection, int position, Object view)
    {
        collection.removeView((View) view);
    }

}
