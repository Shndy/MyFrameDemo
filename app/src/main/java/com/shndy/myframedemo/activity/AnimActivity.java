package com.shndy.myframedemo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.shndy.myframedemo.R;

public class AnimActivity extends BaseActivity implements Animation.AnimationListener {

    private ImageView anim_imgView;
    private Animation myAnimation;
    //message.what的值
    private static final int WHAT = 771;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏显示动画，必须写在setContentView前
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getLayoutId();
        initView();
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_anim;
    }

    /*
     * 初始化控件
     */
    @Override
    public void initView() {
        anim_imgView = (ImageView) findViewById(R.id.anim_imgView);
    }

    @Override
    public void initData() {
        setAnim();
    }

    /*
     * 接收线程发出的消息
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //加载渐变动画(变淡效果)
            myAnimation = AnimationUtils.loadAnimation(AnimActivity.this, R.anim.alpha_tween_disappear);
            //持续时间
            myAnimation.setDuration(3500);
            //结束后任然显示动画最后一帧
            myAnimation.setFillAfter(true);
            anim_imgView.setAnimation(myAnimation);
            //动画监听为当前类
            myAnimation.setAnimationListener(AnimActivity.this);
        }
    };

    private void setAnim() {
        //加载渐变动画(渐现效果)
        myAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_tween_show);
        //持续时间
        myAnimation.setDuration(2000);
        //设置动画
        anim_imgView.setAnimation(myAnimation);
        new Thread() {
            @Override
            public void run() {
                try {
                    //休眠两秒(此时动画全部展示完毕)
                    Thread.sleep(2000);
                    //发送消息，执行下一段动画效果
                    handler.sendEmptyMessage(WHAT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 动画开始时
     */
    @Override
    public void onAnimationStart(Animation animation) {

    }

    /**
     * 动画结束时
     */
    @Override
    public void onAnimationEnd(Animation animation) {
        //存储登陆次数，避免多次加载引导页面
        Intent intent;
        sharedPreferences = this.getSharedPreferences("IsFirst",MODE_PRIVATE);
        int time = sharedPreferences.getInt("count",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("count",++time);
        editor.commit();
        int startTime = sharedPreferences.getInt("count", 0);
        if (startTime == 1) {
            intent = new Intent(AnimActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();
        } else {
            intent = new Intent(AnimActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * 动画播放时
     */
    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
