package com.center.mycode.daohelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.center.mycode.common.Constants;
import com.center.mycode.dao.DaoMaster;
import com.center.mycode.dao.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Zhongxin on 2016/5/27.
 */
public class MyDaoLoader
{
    private static DaoSession daoSession;//得到daoSession，可以执行增删改查操作
    
    private static SQLiteDatabase db;//得到数据库连接对象
    
    private static DaoMaster daoMaster;//得到数据库管理者
    
    private static SQLiteOpenHelper helper;//创建数据库
    
    private static Context context;
    
    // 虚方法，可以无实体内容
    public static void initDataDao(Context mContext)
    {
        initDataDao(mContext, Constants.DATABASE_NAME);
    }
    
    // 虚方法，可以无实体内容
    public static void initDataDao(Context mContext, String DB_NAME)
    {
        helper = new SQLiteOpenHelper(mContext, DB_NAME, null);
        db = helper.getWritableDatabase();
        db.setVersion(2);
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        context = mContext.getApplicationContext();
    }
    
    public static DaoSession getDaoSession()
    {
        if (daoSession == null)
        {
            if (daoMaster == null)
            {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
    
    public static SQLiteDatabase getDb()
    {
        if (db == null)
        {
            db = helper.getWritableDatabase();
        }
        return db;
    }
    
    public static DaoMaster getDaoMaster()
    {
        if (daoMaster == null)
        {
            if (db == null)
            {
                db = helper.getWritableDatabase();
            }
            daoMaster = new DaoMaster(db);
        }
        return daoMaster;
    }
    
    public static SQLiteOpenHelper getOpneHelper(Context mContext)
    {
        if (helper == null)
        {
            helper = new SQLiteOpenHelper(mContext, Constants.DATABASE_NAME, null);
        }
        return helper;
    }
    
    public static void enableQueryBuilderLog()
    {
        
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
}
