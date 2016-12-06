package com.shndy.myframedemo.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shndy.myframedemo.R;
import com.shndy.myframedemo.adapter.GuideViewPager;

import java.util.ArrayList;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    //定义ViewPager对象
    private ViewPager viewPager;

    //定义ViewPager适配器
    private GuideViewPager vpAdapter;

    //定义一个ArrayList来存放View
    private ArrayList<View> views;

    //引导图片资源
    private static final int[] pics = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3, R.drawable.guide_4};

    //底部小点的图片
    private ImageView[] points;

    //记录当前选中位置
    private int currentIndex;

    LinearLayout linearLayout;

    Button btn_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutId();
        initView();
        initData();
        isFirst();
        btnListener();
    }

    private void isFirst() {
    }

    /**
     * 初始化控件
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        //实例化ArrayList对象
        views = new ArrayList<>();

        //实例化ViewPager
        viewPager = (ViewPager) findViewById(R.id.guide_view_pager);

        //实例化ViewPager适配器
        vpAdapter = new GuideViewPager(views);

        //实例化存放小圆点的Layout
        linearLayout = (LinearLayout) findViewById(R.id.dot_image);

        //点击跳转按钮
        btn_in = (Button) findViewById(R.id.btn_in);

        //设置按钮下划线
        btn_in.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        //定义一个布局并设置参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        //初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setBackgroundResource(pics[i]);
            views.add(iv);
        }

        //设置数据
        viewPager.setAdapter(vpAdapter);
        //设置监听
        viewPager.setOnPageChangeListener(this);

        //初始化底部小点
        initPoint();
    }

    /**
     * 初始化小圆点
     */
    private void initPoint() {

        points = new ImageView[pics.length];

        //循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            //得到一个LinearLayout下面的每一个子元素
            points[i] = (ImageView) linearLayout.getChildAt(i);
            //默认都设为灰色
            points[i].setEnabled(true);
            //给每个小点设置监听
            points[i].setOnClickListener(this);
            //设置位置tag，方便取出与当前位置对应
            points[i].setTag(i);
        }

        //设置当面默认的位置
        currentIndex = 0;
        //设置选中状态
        points[currentIndex].setEnabled(false);
    }

    /**
     * 当前页面被滑动
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动状态改变
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 新的页面被选中
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        if (position == 3) {
            linearLayout.setVisibility(View.GONE);
            btn_in.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
            btn_in.setVisibility(View.GONE);
            //设置底部小点选中状态
            setCurDot(position);
        }
    }

    /**
     * 通过点击事件切换当前界面
     * @param view
     */
    @Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        setCurView(position);
        setCurDot(position);
    }

    /**
     * 设置当前页面的位置
     *
     * @param position
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        viewPager.setCurrentItem(position);
    }

    /**
     * 设置小圆点的位置
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length - 1 || currentIndex == position) {
            return;
        }
        points[position].setEnabled(false);
        points[currentIndex].setEnabled(true);

        currentIndex = position;
    }

    private void btnListener(){
        btn_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
