package com.temples.details;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.temples.R;
import com.temples.adapter.TemplePassAdapter;
import com.temples.dashboard.MainActivity;
import com.temples.model.BookingHIstoryDetailData;
import com.temples.network.NetworkHandlerController;
import com.temples.utils.ExpandableHeightListView;
import com.temples.utils.UrlData;

import org.json.JSONObject;

public class BookingHistryDetailActvity extends AppCompatActivity implements NetworkHandlerController.ResultListener {
    TextView visiting_place,visiting_address,visiting_time,bookingvocher;
    Bundle bundle;
    String bookingId;
    BookingHIstoryDetailData mBookingHIstoryDetailData;
    private Toolbar toolbar;
    TextView toolbarTextView,information_display;
    LinearLayout deliver_view;
   ImageView ticket_image;
   ExpandableHeightListView personList;
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
        information_display=findViewById(R.id.information_display);
        deliver_view=findViewById(R.id.deliver_view);
        ticket_image=findViewById(R.id.ticket_image);
        personList=findViewById(R.id.personList);
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
                toolbarTextView.setText(mBookingHIstoryDetailData.getPlaceName());
                if(mBookingHIstoryDetailData.isHomeDelivery()){
                    deliver_view.setVisibility(View.VISIBLE);
                }else{
                    deliver_view.setVisibility(View.GONE);
                }
                visiting_address.setText(mBookingHIstoryDetailData.getDeliveryAddress());
                visiting_time.setText("We have received the Payment of the "+" "+mBookingHIstoryDetailData.getTypeOfPass()+" "+"$"+""+String.valueOf(Integer.parseInt(mBookingHIstoryDetailData.getFeeAmount())+Integer.parseInt(mBookingHIstoryDetailData.getDeliveryCharges()+3)));
                bookingvocher.setText(mBookingHIstoryDetailData.getBookingNumber());
                String termsRegister = "Mr"+" "+ mBookingHIstoryDetailData.getPersonName()+"</br>"+" You have book the "+mBookingHIstoryDetailData.getTypeOfPass()+" "+ "and your Travel Date is"+

                " "+mBookingHIstoryDetailData.getVisitingDate()+" Note : Show the SMS in the Entrance of the Place";
                information_display.setText(Html.fromHtml(termsRegister));

                if (mBookingHIstoryDetailData.getObjVisitingPassMembers() == null || mBookingHIstoryDetailData.getObjVisitingPassMembers().size()>0) {
                    PersonAdapter mChallengeDataAdapter = new PersonAdapter(this,
                            mBookingHIstoryDetailData.getObjVisitingPassMembers());
                    personList.setAdapter(mChallengeDataAdapter);
                    personList.setExpanded(true);
                    mChallengeDataAdapter.notifyDataSetChanged();
                    ticket_image.setVisibility(View.GONE);
                }

            }
        }else{

        }
    }
}
