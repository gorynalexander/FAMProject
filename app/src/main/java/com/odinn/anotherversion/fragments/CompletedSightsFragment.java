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

//
public class CompletedSightsFragment extends AbstractTabFragment implements OnSightMovedListener {

    private RecyclerView rvSightsList;

    public static CompletedSightsFragment getInstance(@Nullable Bundle args) {
        if (args == null) {
            args = new Bundle();
        }
        CompletedSightsFragment fragment = new CompletedSightsFragment();
        fragment.setArguments(args);
        fragment.setTitle("Passed Places");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //this.setTitle(getActivity().getString(R.string.tab_items_passed));
        view = inflater.inflate(R.layout.fragment_exist, container, false);

        rvSightsList = (RecyclerView) view.findViewById(R.id.rvSightsList);
        rvSightsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        SightListAdapter adapter = new SightListAdapter(createMockSightListData());
        try{
            MainActivity activity = (MainActivity) getActivity();
            adapter.setListener(activity.getTpAdapter().getExistItemsFragment());
        } catch (Exception e){
            e.printStackTrace();
        }
        rvSightsList.setAdapter(adapter);

        return view;
    }

    private List<Sight> createMockSightListData() {
        List<Sight> data = new ArrayList<>();
        // data.add(new Sight(0, "Item 1", R.drawable.opera, 46.581422, 30.808210));


        return data;
    }

    @Override
    public void onSightMoved(Sight sight) {
        ((SightListAdapter) rvSightsList.getAdapter()).add(sight);
    }
}
