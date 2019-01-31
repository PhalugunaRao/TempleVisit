package com.temples.details;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.ravindu1024.indicatorlib.ViewPagerIndicator;
import com.temples.R;
import com.temples.adapter.SimplePagerGallery;
import com.temples.adapter.TemplePassAdapter;
import com.temples.model.ParkModel;
import com.temples.model.TempleDetailsData;
import com.temples.network.NetworkHandlerController;
import com.temples.utils.ExpandableHeightListView;
import com.temples.utils.UrlData;

import org.json.JSONObject;

import java.util.HashMap;

public class TempleDetailsPage extends AppCompatActivity implements NetworkHandlerController.ResultListener{
    TextView aboutTemple,ratingCount,viewMore;
    ExpandableHeightListView templeExpandbleList;
    ViewPagerIndicator indicator;
    TempleDetailsData mTempleDetailsData;
    ViewPager pager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temple_details_page);
        initViews();
        Thread thread = new Thread(new GetTempleDetailsThread());
        thread.start();
    }

    private void initViews() {
        indicator = findViewById(R.id.pager_indicator);
        pager = findViewById(R.id.pager);
        aboutTemple= findViewById(R.id.temple_detail_about);
        ratingCount=findViewById(R.id.temple_detail_rating_count);
        viewMore=findViewById(R.id.temple_detail_view_more);
        templeExpandbleList=findViewById(R.id.temple_details_packages);

    }
    private class GetTempleDetailsThread implements Runnable {
        @Override
        public void run() {
            getTempleDetails();
        }
    }

    private void getTempleDetails() {

        String url = UrlData.TEMPLE_DETAILS;
        NetworkHandlerController.getInstance().volleyGetRequestT(this, url,
                this, "temple_details");

    }

    @Override
    public void onResult(boolean isSuccess, JSONObject resultObject, VolleyError volleyError, ProgressDialog progressDialog, String from) {
        if(isSuccess){
           mTempleDetailsData = new Gson().fromJson(resultObject.toString(), TempleDetailsData.class);

           if(mTempleDetailsData!=null){


               aboutTemple.setText(mTempleDetailsData.getAboutPlace());
               ratingCount.setText(mTempleDetailsData.getNumberOfRatings()+" "+"Ratings");

               if (mTempleDetailsData.getPlaceImage() != null) {
                   if (mTempleDetailsData.getPlaceImage().size() > 0) {
                       if (mTempleDetailsData.getPlaceImage().size() > 0
                               ) {
                           SimplePagerGallery adapter=new SimplePagerGallery(TempleDetailsPage.this, mTempleDetailsData.getPlaceImage());
                           if(pager.getAdapter() == null){
                               pager.setAdapter(adapter);
                               if(mTempleDetailsData.getPlaceImage().size()>1){
                                   indicator.setPager(pager);
                               }
                               adapter.notifyDataSetChanged();
                           }
                       }

                   }
               }
           }


           if(mTempleDetailsData.getObjTemplePasses()!=null){
               TemplePassAdapter mChallengeDataAdapter = new TemplePassAdapter(this,
                       mTempleDetailsData.getObjTemplePasses());
               templeExpandbleList.setAdapter(mChallengeDataAdapter);
               templeExpandbleList.setExpanded(true);
               mChallengeDataAdapter.notifyDataSetChanged();
           }



        }else{

        }

    }
}