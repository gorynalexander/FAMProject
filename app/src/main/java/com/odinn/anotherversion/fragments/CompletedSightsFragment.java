package com.odinn.anotherversion.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odinn.anotherversion.R;


public class CompletedSightsFragment extends AbstractTabFragment {

    public static CompletedSightsFragment getInstance(@Nullable Bundle args) {
        if (args == null) {
            args = new Bundle();
        }
        CompletedSightsFragment fragment = new CompletedSightsFragment();
        fragment.setArguments(args);

        Context context = fragment.getActivity();
        fragment.setTitle(context.getString(R.string.tab_items_passed));
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exist, container, false);
    }
}
