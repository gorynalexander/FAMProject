package com.odinn.anotherversion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odinn.anotherversion.MainActivity;
import com.odinn.anotherversion.OnSightMovedListener;
import com.odinn.anotherversion.R;
import com.odinn.anotherversion.adapter.SightListAdapter;
import com.odinn.anotherversion.adapter.TabsPagerFragmentAdapter;
import com.odinn.anotherversion.models.Sights;

import java.util.ArrayList;
import java.util.List;


public class ExistSightsFragment extends AbstractTabFragment implements OnSightMovedListener {

    private RecyclerView rvSightsList;

    public static ExistSightsFragment getInstance(@Nullable Bundle args) {
        if (args == null) {
            args = new Bundle();
        }
        ExistSightsFragment fragment = new ExistSightsFragment();
        fragment.setArguments(args);
        fragment.setTitle("Exist Sights");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.setTitle(getActivity().getString(R.string.tab_items_exist));

        view = inflater.inflate(R.layout.fragment_exist, container, false);
        rvSightsList = (RecyclerView) view.findViewById(R.id.rvSightsList);
        rvSightsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        SightListAdapter adapter = new SightListAdapter(createMockSightListData());
        try{
            MainActivity activity = (MainActivity) getActivity();
            adapter.setListener(activity.getAdapter().getCompletedItemsFragment());
        } catch (Exception e){
            e.printStackTrace();
        }
        rvSightsList.setAdapter(adapter);

        return view;
    }

    private List<Sights> createMockSightListData() {
        List<Sights> data = new ArrayList<>();
        data.add(new Sights(0, "Theatre", R.drawable.opera, 46.581422, 30.808210));
        data.add(new Sights(1, "Boulevar on p. Kotovskogo", R.drawable.fontan, 46.583816 , 30.778767));
        //data.add(new Sights(2, "Krijanovka", R.drawable.krijanovka, 2, 2));
        return data;
    }

    @Override
    public void onSightMoved(Sights sights) {
        ((SightListAdapter) rvSightsList.getAdapter()).add(sights);
    }
}