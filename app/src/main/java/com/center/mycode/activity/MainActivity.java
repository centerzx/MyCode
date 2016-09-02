package com.center.mycode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.center.mycode.R;
import com.center.mycode.bean.User;
import com.center.mycode.common.Constants;
import com.center.mycode.face.IUserBiz;
import com.center.mycode.utils.Colorful;
import com.center.mycode.utils.setter.ViewGroupSetter;
import com.kyleduo.switchbutton.SwitchButton;

public class MainActivity extends BaseActivity
{
    private final static String TAG = "MainActivity";
    
    private RelativeLayout talkRL;
    
    private RelativeLayout talkRLN;
    
    private RelativeLayout talkRLP;
    
    private LinearLayout rootview;
    
    private boolean isHasLongClick = false;
    
    private ScaleAnimation pressAnimation1, pressAnimation2;
    
    private AlphaAnimation normalAlpha1, pressAlpha1;
    
    private Retrofit retrofit;
    
    private SwitchButton mSwithBtn;
    
    public Colorful mColorful;
    public  boolean isNight = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        rootview= (LinearLayout)findViewById(R.id.rootview);
        talkRL = (RelativeLayout)findViewById(R.id.talk_rl);
        talkRLN = (RelativeLayout)findViewById(R.id.talk_rl_n);
        talkRLP = (RelativeLayout)findViewById(R.id.talk_rl_p);
        mSwithBtn = (SwitchButton)findViewById(R.id.sb_custom);
        talkRL.setOnTouchListener(new PressToSpeakListen());
        talkRL.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                if (!isHasLongClick)
                {
                    Log.e("onLongClick", "onLongClick");
                    talkRLP.startAnimation(pressAnimation1);
                    talkRLN.startAnimation(normalAlpha1);
                    talkRLN.setVisibility(View.GONE);
                    isHasLongClick = true;
                }
                return false;
            }
        });
        mSwithBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Log.e("mSwithBtn", "" + isChecked);
//                MyApplication.updateNightMode(isChecked);
                changeThemeWithColorful();
            }
        });
        setAnimation();
        setupColorful();
        //沉浸式效果
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//        {
//            View decorView=getWindow().getDecorView();
        //全屏、主体内容占用系统导航栏的空间、主体内容占用系统状态栏的空间
//            int option=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View
// .SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);//导航栏为透明
//        }
        //隐藏ActionBar
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.hide();
    }
    
    //沉浸式模式
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }
    
    /**
     * 设置各个视图与颜色属性的关联
     */
    private void setupColorful() {
        ViewGroupSetter listViewSetter = new ViewGroupSetter(rootview);
//        // 绑定ListView的Item View中的news_title视图，在换肤时修改它的text_color属性
//        listViewSetter.childViewTextColor(R.id.news_title, R.attr.text_color);
        // 构建Colorful对象来绑定View与属性的对象关系
        mColorful = new Colorful.Builder(this)
//            .backgroundDrawable(R.id.rootview, R.attr.root_view_bg)
            // 设置view的背景图片
            .backgroundColor(R.id.rootview, R.attr.root_view_bg)
            // 设置view的背景图片
            .backgroundColor(R.id.btn1, R.attr.btn_bg)
            // 设置背景色
            .textColor(R.id.btn1, R.attr.text_color)
            .backgroundColor(R.id.btn2, R.attr.btn_bg)
            // 设置背景色
            .textColor(R.id.btn2, R.attr.text_color)
            .backgroundColor(R.id.btn3, R.attr.btn_bg)
            // 设置背景色
            .textColor(R.id.btn3, R.attr.text_color)
            // 设置背景色
            .textColor(R.id.talk_press, R.attr.text_color)
            .setter(listViewSetter) // 手动设置setter
            .create(); // 设置文本颜色
    }
    
    // 切换主题
    public void changeThemeWithColorful() {
        if (mColorful==null){
            Log.e("mColorful","mColorful==null");
            return;
        }
        if (isNight) {
            mColorful.setTheme(R.style.DayTheme);
            Log.e("DayTheme","DayTheme");
        } else {
            mColorful.setTheme(R.style.NightTheme);
            Log.e("NightTheme","NightTheme");
        }
        isNight = !isNight;
    }
    
    private class PressToSpeakListen implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    Log.e("event", "ACTION_DOWN");
                    return false;
                case MotionEvent.ACTION_MOVE:
                    Log.e("event", "ACTION_MOVE");
                    return false;
                case MotionEvent.ACTION_UP:
                    Log.e("event", "ACTION_UP");
                    if (isHasLongClick)
                    {
                        talkRLN.startAnimation(pressAlpha1);
                        talkRLP.startAnimation(pressAnimation2);
                        isHasLongClick = false;
                    }
                    return false;
                default:
                    return false;
            }
        }
    }
    
    public void setAnimation()
    {
        pressAnimation1 =
            new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        //设置动画时间  
        pressAnimation1.setDuration(300);
        pressAnimation1.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                
            }
            
            @Override
            public void onAnimationEnd(Animation animation)
            {
                talkRLP.setVisibility(View.VISIBLE);
                talkRLN.setVisibility(View.GONE);
            }
            
            @Override
            public void onAnimationRepeat(Animation animation)
            {
                
            }
        });
        pressAnimation2 =
            new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        //设置动画时间  
        pressAnimation2.setDuration(300);
        pressAnimation2.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                
            }
            
            @Override
            public void onAnimationEnd(Animation animation)
            {
                talkRLP.setVisibility(View.GONE);
                talkRLN.setVisibility(View.VISIBLE);
            }
            
            @Override
            public void onAnimationRepeat(Animation animation)
            {
                
            }
        });
        normalAlpha1 = new AlphaAnimation(1.0f, 0.0f);
        normalAlpha1.setDuration(100);
        pressAlpha1 = new AlphaAnimation(0.0f, 1f);
        pressAlpha1.setDuration(400);
        pressAlpha1.setStartOffset(200);
    }
    
    public void doAppCompat(View v)
    {
        Intent intent = new Intent(this, TestAppCompatActivity.class);
        startActivity(intent);
    }
    
    public void doAppIntro(View v)
    {
        Intent intent = new Intent(this, AppIntroActivity.class);
        startActivity(intent);
    }
    
    public void doNetWork(View v)
    {
        IUserBiz iUserBiz = retrofit.create(IUserBiz.class);
        Call<User> call = iUserBiz.getUsers();
        call.enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(Call<User> call, Response<User> response)
            {
                int code = response.code();
                Log.e(TAG, code + "---" + "normalGet:" + response.body() + "");
            }
            
            @Override
            public void onFailure(Call<User> call, Throwable t)
            {
                Log.e(TAG, "normalGet:" + t.toString() + "");
            }
        });
    }
}
