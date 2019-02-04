package com.temples.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.temples.R;
import com.temples.dashboard.MainActivity;
import com.temples.login.LoginActivity;
import com.temples.utils.PreferenceHelper;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout logoutView;
    TextView profileName,profileMobile,profileEmail;
    PreferenceHelper prefs;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        prefs=new PreferenceHelper(this);
        initView();
        profileEmail.setText(prefs.getEmail());
        profileMobile.setText(prefs.getMobile());
        profileName.setText(prefs.getFullName());
        logoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.ClearAllData();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initView() {
        profileName=findViewById(R.id.profile_name);
        profileMobile=findViewById(R.id.profile_mobile_number);
        profileEmail=findViewById(R.id.profile_email);
        logoutView=findViewById(R.id.profile_logout_view);
    }
}
