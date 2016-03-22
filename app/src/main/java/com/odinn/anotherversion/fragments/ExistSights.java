package com.odinn.anotherversion.fragments;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.odinn.anotherversion.MainActivity;
import com.odinn.anotherversion.R;
import com.odinn.anotherversion.adapter.SightListAdapter;
import com.odinn.anotherversion.dto.SightsDTO;

import java.util.ArrayList;
import java.util.List;


public class ExistSights extends AbstractTabFragment {

    private static int LAYOUT = R.layout.fragment_exist;

    public static ExistSights getInstance(Context context) {

        Bundle args = new Bundle();
        ExistSights fragment = new ExistSights();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_items_exist));
        return fragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycleView);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(new SightListAdapter(createMockSightListData()));


        return view;
    }




    private List<SightsDTO> createMockSightListData() {
        List<SightsDTO> data = new ArrayList<>();
        data.add(new SightsDTO(0, "Item 1", R.drawable.opera));
        data.add(new SightsDTO(1, "Item 2", R.drawable.fontan));
        data.add(new SightsDTO(2, "Item 3", R.drawable.krijanovka));

        return data;
    }


    public void setContext(Context context) {
        this.context = context;
    }


}
