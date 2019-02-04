package com.temples.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.temples.R;
import com.temples.dashboard.fragments.FragmentHistory;
import com.temples.dashboard.fragments.FragmentPlaces;
import com.temples.dashboard.fragments.FragmentProfile;
import com.temples.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PreferenceHelper prefs;
    Bundle b;
    String redirectTag = "";
    ImageView homeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.dash_board_activity);
        prefs = new PreferenceHelper(this);

        b = getIntent().getExtras();
        if (b != null) {
            redirectTag = b.getString("pageStatus");
        }

        initView();
        setupViewPager(viewPager);
        setupTabView();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }

    private void setupTabView() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        // setupTabIcons();

    }


    private void initView() {

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);
        homeButton=findViewById(R.id.home_button);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentPlaces(), "Places");
        adapter.addFragment(new FragmentHistory(), "History");
        adapter.addFragment(new FragmentProfile(), "Others");

        viewPager.setAdapter(adapter);

        if (redirectTag.equalsIgnoreCase("Others")) {

            viewPager.setCurrentItem(2);
        } else if (redirectTag.equalsIgnoreCase("mybookings")) {
            viewPager.setCurrentItem(1);
        } else {
            viewPager.setCurrentItem(0);
        }
    }
    public void gotoFragment(int pos){
        viewPager.setCurrentItem(pos);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
