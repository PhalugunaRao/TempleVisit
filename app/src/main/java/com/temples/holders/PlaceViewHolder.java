package com.temples.holders;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.temples.R;
import com.temples.details.TempleDetailsPage;
import com.temples.login.LoginActivity;
import com.temples.login.RegisterActivity;
import com.temples.model.ParkModel;
import com.temples.utils.PreferenceHelper;

public class PlaceViewHolder extends BaseViewHolder {
    TextView mplacename, mVisitCount;
    TextView meventAddress;
    ImageView placeImage;
    CardView mcardView;
    RatingBar place_rating;

    public PlaceViewHolder(View itemView) {
        super(itemView);
        mplacename = itemView.findViewById(R.id.place_name);
        placeImage = itemView.findViewById(R.id.place_image);
        mcardView = itemView.findViewById(R.id.card_view);
        mVisitCount=itemView.findViewById(R.id.numer_of_visit);
        place_rating=itemView.findViewById(R.id.place_rating);

    }

    @Override
    public void bind(Object object, final Context context, PreferenceHelper prefs, int pos) {
        {
            try {
                final ParkModel.TempleListData internaleventdata = (ParkModel.TempleListData) object;

                if (internaleventdata != null) {
                    place_rating.setRating((float)internaleventdata.getRating());
                    mVisitCount.setText(internaleventdata.getVisitedCount()+" "+"Visited");
                    mplacename.setText(internaleventdata.getPlaceName());
                    if (internaleventdata.getPlaceImage() == null || internaleventdata.getPlaceImage().isEmpty()) {
                        placeImage.setVisibility(View.GONE);
                    } else {
                        placeImage.setVisibility(View.VISIBLE);
                        try{
                            Glide.with(context).load(internaleventdata.getPlaceImage())
                                    .placeholder(R.drawable.ic_bad_internet).error(R.drawable.ic_bad_internet)
                                    .into(placeImage);
                        }catch (Exception e){

                        }
                    }
                    mcardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, TempleDetailsPage.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("templeID",internaleventdata.getVisitingPlaceId());
                            context.startActivity(intent);
                        }
                    });

                }
            } catch (NullPointerException e) {
            }
        }
    }
}
