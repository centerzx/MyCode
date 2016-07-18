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
import android.widget.RelativeLayout;

import com.center.mycode.MyApplication;
import com.center.mycode.R;
import com.center.mycode.bean.User;
import com.center.mycode.common.Constants;
import com.center.mycode.face.IUserBiz;
import com.kyleduo.switchbutton.SwitchButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity
{
    private final static String TAG = "MainActivity";
    
    private RelativeLayout talkRL;
    
    private RelativeLayout talkRLN;
    
    private RelativeLayout talkRLP;
    
    private boolean isHasLongClick = false;
    
    private ScaleAnimation pressAnimation1, pressAnimation2;
    
    private AlphaAnimation normalAlpha1, pressAlpha1;
    
    private Retrofit retrofit;
    
    private SwitchButton mSwithBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
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
                MyApplication.updateNightMode(isChecked);
            }
        });
        setAnimation();
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