package com.temples.dashboard;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.temples.R;
import com.temples.adapter.ViewPagerAdapter;
import com.temples.custom.CustomViewPager;
import com.temples.dashboard.fragments.FragmentHistory;
import com.temples.dashboard.fragments.FragmentPlaces;
import com.temples.dashboard.fragments.FragmentProfile;

public class DashboardActivity extends AppCompatActivity {
    private CustomViewPager viewPager;
    private Fragment currentFragment;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.dash_board_activity);

        initializeView();

        setupViewPager();
        viewPager.setOffscreenPageLimit(2);
        currentFragment = adapter.getItem(0);

        setupTabView();
    }


    private void initializeView() {
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);
    }

    private void setupViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentPlaces(), "Places");
        adapter.addFragment(new FragmentHistory(), "History");
        adapter.addFragment(new FragmentProfile(), "Profile");
        viewPager.setAdapter(adapter);

    }

    private void setupTabView() {
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        try {
                            viewPager.setCurrentItem(tab.getPosition());
                            currentFragment = adapter.getItem(tab.getPosition());
                            /*if (currentFragment instanceof FragmentDashboard) {
                                FragmentDashboard fragmentDashboard = (FragmentDashboard) currentFragment;
                                fragmentDashboard.refreshDataView();
                            }*/
                        } catch (NullPointerException e) {
                        }

                        break;
                    case 1:
                        try {
                            viewPager.setCurrentItem(tab.getPosition());
                            currentFragment = adapter.getItem(tab.getPosition());
                        } catch (NullPointerException e) {
                        }
                        break;
                    case 2:
                        try {
                            viewPager.setCurrentItem(tab.getPosition());
                            currentFragment = adapter.getItem(tab.getPosition());
                        } catch (NullPointerException e) {
                        }
                        break;

                    default:
                        currentFragment = adapter.getItem(tab.getPosition());
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Places");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home_first_tab, 0, 0);
        tabOne.setCompoundDrawablePadding(10);
        tabOne.setSelected(true);
        tabLayout.getTabAt(0).setCustomView(tabOne);
        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("History");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home_first_tab, 0, 0);
        tabTwo.setCompoundDrawablePadding(10);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView three = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        three.setText("Profile");
        three.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home_first_tab, 0, 0);
        three.setCompoundDrawablePadding(10);
        tabLayout.getTabAt(2).setCustomView(three);


    }
}





