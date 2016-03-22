package com.odinn.anotherversion.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.odinn.anotherversion.R;
import com.odinn.anotherversion.fragments.AbstractTabFragment;
import com.odinn.anotherversion.fragments.CompletedSights;
import com.odinn.anotherversion.fragments.ExistSights;

import java.util.HashMap;
import java.util.Map;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter{

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public TabsPagerFragmentAdapter(Context context, FragmentManager fm ) {
        super(fm);
        this.context = context;
        tabs = new HashMap<>();
        tabs.put(0, ExistSights.getInstance(context));
        tabs.put(1, CompletedSights.getInstance(context));
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
