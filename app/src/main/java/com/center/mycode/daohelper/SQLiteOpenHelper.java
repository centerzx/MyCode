package com.center.mycode.daohelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.center.mycode.dao.DaoMaster;
import com.center.mycode.dao.UserDao;

/**
 * Created by Administrator on 2016/5/26.
 */
public class SQLiteOpenHelper extends DaoMaster.OpenHelper
{
    public SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory)
    {
        super(context, name, factory);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by migrating all tables data");
//        if (oldVersion == 1 && newVersion == 2) {
            // 增加一个实体表
            //GradeDao.createTable(db, false);
            // 修改User表
//            db.execSQL("ALTER TABLE 'User' ADD 'RESULTS' REAL");
//        }
        UpdateMigrationHelper.getInstance().migrate(db,UserDao.class);
    }
    
    
}
