package com.center.mycode.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.cmcc.andmu.iothttpsdk.common.ErrorCode;
import com.cmcc.andmu.iothttpsdk.http.BaseResponse;

/**
 * Created by Administrator on 2016/5/19.
 */
public class BaseActivity extends AppCompatActivity
{
    
    /**
     //     * @param context
     //     * @param code
     //     * @param responseStr
     //     * @return
     //     */
    public static boolean filter(Context context, int code, String responseStr)
    {
        if (code == 401)
        {
            if (context instanceof TestGreenDaoActivity)
            {
                Toast.makeText(context,"用户名或密码错误!",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent intent = new Intent(context, TestGreenDaoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("autoLogin", false);
                context.startActivity(intent);
            }
            return true;
        }
        else if (code == 200)
        {
            BaseResponse response = JSON.parseObject(responseStr, BaseResponse.class);
            if (response.code == ErrorCode.ACCOUNT_LOCKED || response.code == ErrorCode.ACCOUNT_FROZEN
                || response.code == ErrorCode.TOKEN_EXPIRE)
            {
                Toast.makeText(context,response.desc,Toast.LENGTH_SHORT).show();
                if (!(context instanceof TestGreenDaoActivity))
                {
                    Intent intent = new Intent(context, TestGreenDaoActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("autoLogin", false);
                    context.startActivity(intent);
                }
            }
        }
        return false;
    }
}
