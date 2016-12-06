package com.shndy.myframedemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shndy.myframedemo.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shndy on 2016/12/6.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    //存放fragment的集合
    private List<BaseFragment> fragments = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
