package com.center.mycode.activity;

import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.center.mycode.R;
import com.center.mycode.view.ColorShades;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class AppIntroActivity extends BaseActivity
{
    private RelativeLayout mRootLayout;
    
    private ViewPager mViewPager;
    
    private SystemBarTintManager mTintManager;
    
    private ColorShades shades;
    
    private  int colorBg[];
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
//            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            setTranslucentStatus(true);
        }
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_app_intro);
        mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setNavigationBarTintEnabled(true);
        applySelectedColor(R.color.light_green_500);
        init();
    }
    
    private void applySelectedColor(int color)
    {
        mTintManager.setTintColor(color);
    }
    
    private void init()
    {
        shades = new ColorShades();
        colorBg = getResources().getIntArray(R.array.splash_bg);
        mRootLayout = (RelativeLayout)findViewById(R.id.rl_root);
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        IntroPager introPager = new IntroPager(R.array.splash_icon, R.array.splash_desc);
        mViewPager.setAdapter(introPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                shades.setFromColor(colorBg[position % colorBg.length])
                    .setToColor(colorBg[(position + 1) % colorBg.length])
                    .setShade(positionOffset);
                mRootLayout.setBackgroundColor(shades.generate());
                applySelectedColor(shades.generate());
            }
            
            @Override
            public void onPageSelected(int position)
            {
                
            }
            
            @Override
            public void onPageScrollStateChanged(int state)
            {
                
            }
        });
    }
    
    public void goToMain(View v)
    {
        finish();
    }
    
    private class IntroPager extends PagerAdapter
    {
        
        private String[] mDescs;
        
        private TypedArray mIcons;
        
        public IntroPager(int icoImage, int des)
        {
            mDescs = getResources().getStringArray(des);
            mIcons = getResources().obtainTypedArray(icoImage);
        }
        
        @Override
        public int getCount()
        {
            return mIcons.length();
        }
        
        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }
        
        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            View itemLayout = getLayoutInflater().inflate(R.layout.layout_app_intro, container, false);
            ImageView mImage = (ImageView)itemLayout.findViewById(R.id.iv_img);
            TextView mTextView = (TextView)itemLayout.findViewById(R.id.tv_desc);
            Button mButton = (Button)itemLayout.findViewById(R.id.btn_launch);
            mImage.setImageResource(mIcons.getResourceId(position, 0));
            mTextView.setText(mDescs[position]);
            if (position == getCount() - 1)
            {
                mButton.setVisibility(View.VISIBLE);
            }
            else
            {
                mButton.setVisibility(View.GONE);
            }
            container.addView(itemLayout);
            return itemLayout;
        }
        
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View)object);
        }
    }
    
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    
}
