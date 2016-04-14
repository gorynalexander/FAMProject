package com.odinn.anotherversion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odinn.anotherversion.R;
import com.odinn.anotherversion.adapter.SightListAdapter;
import com.odinn.anotherversion.models.Sights;

import java.util.ArrayList;
import java.util.List;


public class ExistSightsFragment extends AbstractTabFragment {

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
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rvSightsList);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new SightListAdapter(createMockSightListData()));

        return view;
    }

    private List<Sights> createMockSightListData() {
        List<Sights> data = new ArrayList<>();
        data.add(new Sights(0, "Theatre", R.drawable.opera, 46.581422, 30.808210));
        data.add(new Sights(1, "Boulevar on p. Kotovskogo", R.drawable.fontan, 46.583816 , 30.778767));
        //data.add(new Sights(2, "Krijanovka", R.drawable.krijanovka, 2, 2));
        return data;
    }

}