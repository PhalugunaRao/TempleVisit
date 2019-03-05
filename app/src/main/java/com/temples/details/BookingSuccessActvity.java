package com.temples.details;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.temples.R;
import com.temples.dashboard.MainActivity;
import com.temples.model.BookingHIstoryDetailData;
import com.temples.network.NetworkHandlerController;
import com.temples.utils.CustomCircularProgress;
import com.temples.utils.UrlData;

import org.json.JSONObject;

public class BookingSuccessActvity extends AppCompatActivity implements NetworkHandlerController.ResultListener {
    TextView visiting_place,visiting_address,visiting_time,done_book,bookingIdNumber;
    Bundle bundle;
    String bookingId;
    BookingHIstoryDetailData mBookingHIstoryDetailData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_success_activity);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            bookingId = bundle.getString("bookingId", "");
        }
        initview();
        CustomCircularProgress.getInstance().show(this);
        Thread thread = new Thread(new GetPackageDetailsThread());
        thread.start();

        done_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingSuccessActvity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initview() {
        visiting_place=findViewById(R.id.visiting_place);
        visiting_address=findViewById(R.id.visiting_address);
        visiting_time=findViewById(R.id.visiting_time);
        done_book=findViewById(R.id.done_book);
        bookingIdNumber=findViewById(R.id.booking_id_number);
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
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissDialog();
    }
    public void dismissDialog() {
        if (!isFinishing() && CustomCircularProgress.getInstance() != null)
            CustomCircularProgress.getInstance().dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(BookingSuccessActvity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onResult(boolean isSuccess, JSONObject resultObject, VolleyError volleyError, ProgressDialog progressDialog, String from) {
        dismissDialog();
        if (isSuccess){
            mBookingHIstoryDetailData= new Gson().fromJson(resultObject.toString(),BookingHIstoryDetailData.class);
            if(mBookingHIstoryDetailData!=null){
                visiting_place.setText(mBookingHIstoryDetailData.getPlaceName());
                bookingIdNumber.setText(mBookingHIstoryDetailData.getBookingNumber());
                visiting_time.setText("We have received the Payment of the "+" "+mBookingHIstoryDetailData.getTypeOfPass()+" "+"$"+""+String.valueOf(Integer.parseInt(mBookingHIstoryDetailData.getFeeAmount())+Integer.parseInt(mBookingHIstoryDetailData.getDeliveryCharges()+3)));
                String termsRegister = "Mr"+" "+ mBookingHIstoryDetailData.getPersonName()+"</br>"+" You have book the "+mBookingHIstoryDetailData.getTypeOfPass()+" "+ "and your Travel Date is"+

                        " "+mBookingHIstoryDetailData.getVisitingDate()+" Note : Show the SMS in the Entrance of the Place";
                visiting_address.setText(Html.fromHtml(termsRegister));

            }
        }else{

        }
    }
}
