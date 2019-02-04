package com.temples.holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.temples.R;
import com.temples.details.BookingHistryDetailActvity;
import com.temples.details.BookingSuccessActvity;
import com.temples.details.PackageDetailsActivity;
import com.temples.details.TempleDetailsPage;
import com.temples.model.HistoryModel;
import com.temples.model.ParkModel;
import com.temples.utils.PreferenceHelper;

public class HistoryViewHolder extends BaseViewHolder {
    TextView mplacename, typeOfPass,placedate;
    CardView mcardView;

    public HistoryViewHolder(View itemView) {
        super(itemView);
        mplacename = itemView.findViewById(R.id.history_place_name);
        typeOfPass = itemView.findViewById(R.id.typeOfPass);
        mcardView = itemView.findViewById(R.id.card_view);
        placedate=itemView.findViewById(R.id.place_date);

    }

    @Override
    public void bind(Object object, final Context context, PreferenceHelper prefs, int pos) {
        {
            try {
                final HistoryModel.HistoryModelData internaleventdata = (HistoryModel.HistoryModelData) object;

                if (internaleventdata != null) {
                    mplacename.setText(internaleventdata.getPlaceName());
                    typeOfPass.setText("Duration : "+" "+internaleventdata.getTypeOfPass());
                    placedate.setText("Travell Date :"+" "+internaleventdata.getVisitingDate());

                    mcardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, BookingHistryDetailActvity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("bookingId",internaleventdata.getBookingId());
                            context.startActivity(intent);
                        }
                    });

                }
            } catch (NullPointerException e) {
            }
        }
    }
}
