package com.temples.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.temples.login.WelcomeOne;
import com.temples.login.WelcomeThree;
import com.temples.login.WelcomeTwo;

public class SplashAdapter extends FragmentPagerAdapter {
    public SplashAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new WelcomeOne();
            case 1:
                return new WelcomeTwo();
            case 2:
                return new WelcomeThree();
            default:
                return null;
        }
    }
}
