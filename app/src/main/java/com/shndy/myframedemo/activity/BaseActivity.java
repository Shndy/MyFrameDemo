package com.shndy.myframedemo.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Shndy on 2016/12/5.
 */

public abstract class BaseActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载xml窗体
        setContentView(getLayoutId());
        initView();
        initData();
        initListener();
    }

    /**
     * 得到子类传来的Layout
     */
    public abstract int getLayoutId();

    /**
     * 初始化使用
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化监听事件
     */
    public void initListener() {

    }

    /**
     * RadioGroup 监听事件
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
    }

    /**
     * 吐司方法
     * @param toast 需要吐司的内容
     */
    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
