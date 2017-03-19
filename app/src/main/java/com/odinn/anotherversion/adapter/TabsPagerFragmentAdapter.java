package com.odinn.anotherversion.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.odinn.anotherversion.fragments.AbstractTabFragment;
import com.odinn.anotherversion.fragments.ArchitectureTabFragment;
import com.odinn.anotherversion.fragments.CompletedSightsFragment;
import com.odinn.anotherversion.fragments.ExistSightsFragment;
import com.odinn.anotherversion.fragments.HistoricalTabFragment;
import com.odinn.anotherversion.fragments.LandscapesTabFragment;

import java.util.ArrayList;

public class TabsPagerFragmentAdapter extends FragmentStatePagerAdapter{

    private ArrayList<AbstractTabFragment> tabs = new ArrayList<>();

    public TabsPagerFragmentAdapter(FragmentManager fm ) {
        super(fm);
        tabs.add(ExistSightsFragment.getInstance(null));
        tabs.add(LandscapesTabFragment.getInstance(null));
        tabs.add(ArchitectureTabFragment.getInstance(null));
        tabs.add(HistoricalTabFragment.getInstance(null));
    }
    
    public CompletedSightsFragment getCompletedItemsFragment(){
        for (AbstractTabFragment tab : tabs) {
            if (tab instanceof CompletedSightsFragment) return (CompletedSightsFragment) tab;
        }
        return null;
    }
    
    public ExistSightsFragment getExistItemsFragment(){
        for (AbstractTabFragment tab : tabs) {
            if (tab instanceof ExistSightsFragment) return (ExistSightsFragment) tab;
        }
        return null;
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
