package com.odinn.anotherversion.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.odinn.anotherversion.fragments.AbstractTabFragment;
import com.odinn.anotherversion.fragments.CompletedSightsFragment;
import com.odinn.anotherversion.fragments.ExistSightsFragment;

import java.util.ArrayList;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter{

    private ArrayList<AbstractTabFragment> tabs = new ArrayList<>();

    public TabsPagerFragmentAdapter(FragmentManager fm ) {
        super(fm);
        tabs.add(ExistSightsFragment.getInstance(null));
        tabs.add(CompletedSightsFragment.getInstance(null));
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}
