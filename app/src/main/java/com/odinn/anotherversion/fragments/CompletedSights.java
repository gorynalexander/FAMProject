package com.odinn.anotherversion.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.odinn.anotherversion.R;


public class CompletedSights extends AbstractTabFragment {

    private static int LAYOUT = R.layout.fragment_exist;


    public static CompletedSights getInstance(Context context) {

        Bundle args = new Bundle();
        CompletedSights fragment = new CompletedSights();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_items_passed));
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);


        return view;
    }


    public void setContext(Context context) {
        this.context = context;
    }
}
