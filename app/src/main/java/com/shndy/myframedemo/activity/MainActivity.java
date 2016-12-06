package com.shndy.myframedemo.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.shndy.myframedemo.R;
import com.shndy.myframedemo.adapter.FragmentAdapter;
import com.shndy.myframedemo.fragment.BaseFragment;
import com.shndy.myframedemo.fragment.CategoryFragment;
import com.shndy.myframedemo.fragment.FunFragment;
import com.shndy.myframedemo.fragment.HomeFragment;
import com.shndy.myframedemo.fragment.MyFragment;
import com.shndy.myframedemo.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private RadioGroup radio_group;
    private ViewPager home_view_pager;
    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        home_view_pager = (ViewPager) findViewById(R.id.home_view_pager);
    }

    @Override
    public void initData() {
        fragments.add(new HomeFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new FunFragment());
        fragments.add(new ShopFragment());
        fragments.add(new MyFragment());
        home_view_pager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
    }

    @Override
    public void initListener() {
        super.initListener();
        radio_group.setOnCheckedChangeListener(this);
        home_view_pager.setOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        super.onCheckedChanged(radioGroup, i);
        switch (i) {
            case R.id.rb_home:
                //int为页数，boolean为状态
                home_view_pager.setCurrentItem(0, false);
                break;
            case R.id.rb_category:
                home_view_pager.setCurrentItem(1, false);
                break;
            case R.id.rb_fun:
                home_view_pager.setCurrentItem(2, false);
                break;
            case R.id.rb_shopping:
                home_view_pager.setCurrentItem(3, false);
                break;
            case R.id.rb_mycenter:
                home_view_pager.setCurrentItem(4, false);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radio_group.check(R.id.rb_home);
                break;
            case 1:
                radio_group.check(R.id.rb_category);
                break;
            case 2:
                radio_group.check(R.id.rb_fun);
                break;
            case 3:
                radio_group.check(R.id.rb_shopping);
                break;
            case 4:
                radio_group.check(R.id.rb_mycenter);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
