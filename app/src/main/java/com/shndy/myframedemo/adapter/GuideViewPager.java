package com.shndy.myframedemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 引导界面View Pager适配器，绑定数据和View
 * Created by Shndy on 2016/12/4.
 */

public class GuideViewPager extends PagerAdapter {

    //界面列表
    private ArrayList<View> views;

    /**
     * 构造方法
     * @param views
     */
    public GuideViewPager(ArrayList<View> views) {
        this.views = views;
    }

    /**
     * 获得当前页面数
     * @return
     */
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    /**
     * 初始化position位置的界面
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position), 0);
        return views.get(position);
    }

    /**
     * 判断是否由对象生成界面
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    /**
     * 销毁position位置的view
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
