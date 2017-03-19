package com.odinn.anotherversion.fragments;


import android.support.v4.app.Fragment;
import android.view.View;


public class AbstractTabFragment extends Fragment{

    private String title;
    protected View view;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
