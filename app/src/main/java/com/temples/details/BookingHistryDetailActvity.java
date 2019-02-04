package com.temples.details;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.temples.R;
import com.temples.dashboard.MainActivity;
import com.temples.model.BookingHIstoryDetailData;
import com.temples.network.NetworkHandlerController;
import com.temples.utils.UrlData;

import org.json.JSONObject;

public class BookingHistryDetailActvity extends AppCompatActivity implements NetworkHandlerController.ResultListener {
    TextView visiting_place,visiting_address,visiting_time,bookingvocher;
    Bundle bundle;
    String bookingId;
    BookingHIstoryDetailData mBookingHIstoryDetailData;
    private Toolbar toolbar;
    TextView toolbarTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detail_activity);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            bookingId = bundle.getString("bookingId", "");
        }
        initview();
        setUpToolBar();
        Thread thread = new Thread(new GetPackageDetailsThread());
        thread.start();

    }
    private void setUpToolBar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTextView.setText("My Booking Details");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initview() {
        toolbar = findViewById(R.id.common_toolbar);
        toolbarTextView=findViewById(R.id.common_toolbarText);
        visiting_place=findViewById(R.id.visiting_place);
        visiting_address=findViewById(R.id.visiting_address);
        visiting_time=findViewById(R.id.visiting_time);
        bookingvocher=findViewById(R.id.booking_id_number);
    }

    private class GetPackageDetailsThread implements Runnable {
        @Override
        public void run() {
            getPackageDetails();
        }
    }

    private void getPackageDetails() {

        String url = UrlData.BOOKING_INFO+bookingId;
        System.out.println("PackageDetailsActivity.getPackageDetails==="+url);
        NetworkHandlerController.getInstance().volleyGetRequestT(this, url,
                this, "package_details");

    }

    @Override
    public void onResult(boolean isSuccess, JSONObject resultObject, VolleyError volleyError, ProgressDialog progressDialog, String from) {
        if (isSuccess){
            mBookingHIstoryDetailData= new Gson().fromJson(resultObject.toString(),BookingHIstoryDetailData.class);
            if(mBookingHIstoryDetailData!=null){
                visiting_place.setText(mBookingHIstoryDetailData.getPlaceName());
                visiting_address.setText(mBookingHIstoryDetailData.getPlaceAddress());
                visiting_time.setText("Your journey Date: "+mBookingHIstoryDetailData.getVisitingDate());
                bookingvocher.setText(mBookingHIstoryDetailData.getBookingNumber());

            }
        }else{

        }
    }
}
