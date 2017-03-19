package com.odinn.anotherversion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odinn.anotherversion.MainActivity;
import com.odinn.anotherversion.helper.OnSightMovedListener;
import com.odinn.anotherversion.R;
import com.odinn.anotherversion.adapter.SightListAdapter;
import com.odinn.anotherversion.models.Sight;

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
        fragment.setTitle("All");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //this.setTitle(getActivity().getString(R.string.tab_items_exist));

        view = inflater.inflate(R.layout.fragment_exist, container, false);
        rvSightsList = (RecyclerView) view.findViewById(R.id.rvSightsList);
        rvSightsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        SightListAdapter adapter = new SightListAdapter(createMockSightListData());
        try{
            MainActivity activity = (MainActivity) getActivity();
            adapter.setListener(activity.getTpAdapter().getCompletedItemsFragment());
        } catch (Exception e){
            e.printStackTrace();
        }
        rvSightsList.setAdapter(adapter);

        return view;
    }

    private List<Sight> createMockSightListData() {
        List<Sight> data = new ArrayList<>();
        data.add(new Sight(0, "Academy", R.drawable.ogah, 46.487151, 30.731533));
        data.add(new Sight(1, "Old Odessa", R.drawable.starayaodessa, 46.490295, 30.736302));
        data.add(new Sight(3, "Warriors", R.drawable.voini, 46.479325, 30.758717));
        data.add(new Sight(4, "Dangerous road", R.drawable.kanatnaya, 46.463623, 30.759435));
        data.add(new Sight(5, "Place of Glory", R.drawable.kurgan, 46.626777, 30.834209));
        return data;
    }

    @Override
    public void onSightMoved(Sight sight) {
        ((SightListAdapter) rvSightsList.getAdapter()).add(sight);
    }
}