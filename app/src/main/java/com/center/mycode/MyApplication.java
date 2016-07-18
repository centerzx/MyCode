package com.center.mycode;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.center.mycode.daohelper.MyDaoLoader;

/**
 * Created by Administrator on 2016/5/18.
 */
public class MyApplication extends Application
{
    private static MyApplication instance;
    
    public static Resources sRes;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        MyDaoLoader.initDataDao(this);
        MyDaoLoader.enableQueryBuilderLog();
        init(instance);
    }
    
    public static void init(Context context)
    {
        sRes = context.getResources();
    }
    
    public static void updateNightMode(boolean on)
    {
        DisplayMetrics dm = sRes.getDisplayMetrics();
        Configuration config = sRes.getConfiguration();
        config.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
        config.uiMode |= on ? Configuration.UI_MODE_NIGHT_YES : Configuration.UI_MODE_NIGHT_NO;
        sRes.updateConfiguration(config, dm);
    }
}
