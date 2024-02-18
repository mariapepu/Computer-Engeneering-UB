package com.example.coinnote.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.coinnote.view.fragment.Grid_notes;
import com.example.coinnote.view.fragment.Calend;

public class PagerController extends FragmentPagerAdapter {

    int numOfTabs;

    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Grid_notes();
            case 1:
                return new Calend();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
