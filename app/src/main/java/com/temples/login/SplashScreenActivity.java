package com.temples.login;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.temples.R;
import com.temples.adapter.SplashAdapter;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SplashScreenActivity extends FragmentActivity {
    private SplashAdapter mAdapter;
    private ViewPager mPager;
    ImageButton circleOne, circleTwo, circleThree;
    TextView skipButton, getStartedButton;
    LinearLayout skpiView;
    LinearLayout getStartedView;
   // private PreferenceHelper prefs;
    LinearLayout beforeView;

    String type = "";
    String title = "";
    String message = "";
    JSONObject payloadData;
    ImageView gilrCycleImageView;
    String redirectPage="";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Fabric.with(this, new Crashlytics());
        setContentView(R.layout.splash_screen);
        //prefs = new PreferenceHelper(this);
        try {
            Bundle extras = getIntent().getExtras();
            type = extras.getString("type");
            title = extras.getString("title");
            message = extras.getString("message");
            payloadData = new JSONObject(extras.getString("data"));
        } catch (Exception e) {

        }

        initView();

        new CountDownTimer(1500, 1000) { // 5000 = 5 sec
            public void onTick(long millisUntilFinished) {
                Display display = getWindow().getWindowManager().getDefaultDisplay();
                float values = Float.parseFloat(String.valueOf((display.getWidth() * 0.75) - (getResources().getDimension(R.dimen.margin_40))));
                ObjectAnimator animation = ObjectAnimator.ofFloat(gilrCycleImageView, "translationX", values);
                animation.setDuration(1000);
                animation.start();
            }


            public void onFinish() {

                beforeView.setVisibility(View.GONE);
                mAdapter = new SplashAdapter(getSupportFragmentManager());
                mPager = findViewById(R.id.pager);
                mPager.setAdapter(mAdapter);
                mPager.setOffscreenPageLimit(1);

                mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        switch (position) {
                            case 0:
                                skpiView.setVisibility(View.VISIBLE);
                                getStartedView.setVisibility(View.GONE);
                                circleOne.setBackgroundResource(R.drawable.black_circle);
                                circleTwo.setBackgroundResource(R.drawable.white_circle);
                                circleThree.setBackgroundResource(R.drawable.white_circle);
                                break;
                            case 1:
                                skpiView.setVisibility(View.VISIBLE);
                                getStartedView.setVisibility(View.GONE);
                                circleOne.setBackgroundResource(R.drawable.white_circle);
                                circleTwo.setBackgroundResource(R.drawable.black_circle);
                                circleThree.setBackgroundResource(R.drawable.white_circle);
                                break;
                            case 2:
                                skpiView.setVisibility(View.GONE);
                                getStartedView.setVisibility(View.VISIBLE);
                                circleOne.setBackgroundResource(R.drawable.white_circle);
                                circleTwo.setBackgroundResource(R.drawable.white_circle);
                                circleThree.setBackgroundResource(R.drawable.black_circle);
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

               /* if (!prefs.getIsLogin()) {
                    beforeView.setVisibility(View.GONE);
                    mAdapter = new SplashAdapter(getSupportFragmentManager());
                    mPager = findViewById(R.id.pager);
                    mPager.setAdapter(mAdapter);
                    mPager.setOffscreenPageLimit(1);

                    mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {

                            switch (position) {
                                case 0:
                                    skpiView.setVisibility(View.VISIBLE);
                                    getStartedView.setVisibility(View.GONE);
                                    circleOne.setBackgroundResource(R.drawable.black_circle);
                                    circleTwo.setBackgroundResource(R.drawable.white_circle);
                                    circleThree.setBackgroundResource(R.drawable.white_circle);
                                    break;
                                case 1:
                                    skpiView.setVisibility(View.VISIBLE);
                                    getStartedView.setVisibility(View.GONE);
                                    circleOne.setBackgroundResource(R.drawable.white_circle);
                                    circleTwo.setBackgroundResource(R.drawable.black_circle);
                                    circleThree.setBackgroundResource(R.drawable.white_circle);
                                    break;
                                case 2:
                                    skpiView.setVisibility(View.GONE);
                                    getStartedView.setVisibility(View.VISIBLE);
                                    circleOne.setBackgroundResource(R.drawable.white_circle);
                                    circleTwo.setBackgroundResource(R.drawable.white_circle);
                                    circleThree.setBackgroundResource(R.drawable.black_circle);
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                } else {
                   *//* Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();*//*




                }*/
            }
        }.start();


        //Skpi Button
        skipButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                if (mPager != null)
                    mPager.setCurrentItem(2);
            }
        });


        //Go to LoginPage
        getStartedButton.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initView() {
        beforeView = findViewById(R.id.before_view);
        skpiView = findViewById(R.id.skipButton_view);
        skipButton = findViewById(R.id.skipButton);
        getStartedButton = findViewById(R.id.get_started_Button);
        circleOne = findViewById(R.id.circle_one);
        circleTwo = findViewById(R.id.circle_two);
        circleThree = findViewById(R.id.circle_three);
        getStartedView = findViewById(R.id.get_started_view);
    }
}