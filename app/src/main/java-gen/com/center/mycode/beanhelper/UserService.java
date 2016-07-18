package com.center.mycode.beanhelper;

import android.text.TextUtils;

import com.center.mycode.bean.User;
import com.center.mycode.dao.UserDao;
import com.center.mycode.daohelper.DaoBaseService;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/5/30.
 */
public class UserService extends DaoBaseService
{
    private static UserDao userDao;
    
    public UserService(UserDao dao)
    {
        super(dao);
        userDao = dao;
    }
    
    public boolean hasKey(String key)
    {
        if (userDao == null || TextUtils.isEmpty(key))
        {
            return false;
        }
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Id.eq(key));
        long count = qb.buildCount().count();
        return count > 0;
    }
    
    public User queryById(long orderItemId)
    {
        if (userDao != null)
        {
            return userDao.loadByRowId(orderItemId);
        }
        return null;
    }
    
    public List<User> queryByName(String name)
    {
        if (userDao != null)
        {
            List<User> userList = userDao.queryBuilder().where(UserDao.Properties.Name.like("%"+name+"%")).list();
            return userList;
        }
        return null;
    }
}
