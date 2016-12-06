package com.shndy.myframedemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Shndy on 2016/12/6.
 */

public abstract class BaseFragment extends Fragment {
    public Context context;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        context = getActivity();
        View view = View.inflate(context, getLayoutId(), null);
        initView(view);
        initData();
        initListener();
        return view;
    }

    /**
     * 初始化Fragment控件
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 初始化Fragment数据
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    /**
     * 初始化Fragment
     * @return
     */
    protected abstract int getLayoutId();

    public  void showToast(String s){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }
}
