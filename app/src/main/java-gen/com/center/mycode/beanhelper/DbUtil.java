package com.center.mycode.beanhelper;

import com.center.mycode.dao.UserDao;
import com.center.mycode.daohelper.MyDaoLoader;

/**
 * Created by Administrator on 2016/5/30.
 */
public class DbUtil
{
    private static UserService userService;
    
    private static UserDao getUserDao()
    {
        return MyDaoLoader.getDaoSession().getUserDao();
    }
    
    public static UserService getUserService()
    {
        if (userService == null)
        {
            userService = new UserService(getUserDao());
        }
        return userService;
    }
}
