package com.temples.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.temples.R;
import com.temples.login.LoginActivity;
import com.temples.profile.ProfileActivity;
import com.temples.utils.PreferenceHelper;

public class MainActivity extends AppCompatActivity {

    LinearLayout list_of_place_view,my_bookings,my_profiles,other_views;
    TextView user_name_lable;
    ImageView userProfileIcon;
    PreferenceHelper prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.mainactivity_layout);
        prefs = new PreferenceHelper(this);
        initializeView();
        user_name_lable.setText(prefs.getFullName());

        list_of_place_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                intent.putExtra("pageStatus","places");
                startActivity(intent);
            }
        });

        my_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                intent.putExtra("pageStatus","mybookings");
                startActivity(intent);
            }
        });
        my_profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        other_views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                intent.putExtra("pageStatus","Others");
                startActivity(intent);
            }
        });
        userProfileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }


    private void initializeView() {
        other_views=findViewById(R.id.other_views);
        my_bookings=findViewById(R.id.my_bookings);
        my_profiles=findViewById(R.id.my_profiles);
        list_of_place_view=findViewById(R.id.list_of_place_view);
        user_name_lable=findViewById(R.id.user_name_lable);
        userProfileIcon=findViewById(R.id.user_profile_icon);


    }




}





