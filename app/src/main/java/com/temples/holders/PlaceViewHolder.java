package com.temples.holders;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.temples.R;
import com.temples.model.ParkModel;
import com.temples.utils.PreferenceHelper;

public class PlaceViewHolder extends BaseViewHolder {
    TextView meventName, meventPriceTag, meventGoingTag;
    TextView meventDayName, meventDayDate, meventDayMonth, eventDateFullFormat;
    TextView meventAddress;
    ImageView eventImage;
    CardView knowMoreEvent;

    public PlaceViewHolder(View itemView) {
        super(itemView);
        meventName = itemView.findViewById(R.id.event_name);
        meventAddress = itemView.findViewById(R.id.event_address);
        eventImage = itemView.findViewById(R.id.event_image);
        knowMoreEvent = itemView.findViewById(R.id.card_view);


    }

    @Override
    public void bind(Object object, final Context context, PreferenceHelper prefs, int pos) {
        {
            try {
                final ParkModel.TempleListData internaleventdata = (ParkModel.TempleListData) object;

                if (internaleventdata != null) {


                    meventName.setText(internaleventdata.getTempleName());
                    meventAddress.setText(internaleventdata.getAboutTemple());

                    if (internaleventdata.getTempleImage() == null || internaleventdata.getTempleImage().isEmpty()) {

                        eventImage.setVisibility(View.GONE);
                    } else {
                        eventImage.setVisibility(View.VISIBLE);
                        try{
                            Glide.with(context).load(internaleventdata.getTempleImage())
                                    .placeholder(R.drawable.ic_bad_internet).error(R.drawable.ic_bad_internet)
                                    .into(eventImage);
                        }catch (Exception e){

                        }
                    }
                    knowMoreEvent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                }
            } catch (NullPointerException e) {
            }
        }
    }
}
