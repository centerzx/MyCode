package test.center.com.mylibrary.http;

import android.content.Context;
import android.util.Log;

import test.center.com.mylibrary.BuildConfig;

/**
 * Created by MaJian on 2015/12/21.
 */
class IotHttpStatusListener implements HttpTaskListener
{
    private Context mContext;
    
    private String mMsg;
    
    private HttpResponseHandler mHandler;
    
    public IotHttpStatusListener()
    {
        
    }
    
    public IotHttpStatusListener(Context context, String msg, HttpResponseHandler handler)
    {
        mContext = context;
        mMsg = msg;
        mHandler = handler;
    }

//    /**
//     * @param context
//     * @param code
//     * @param responseStr
//     * @return
//     */
//    private static boolean filter(Context context, int code, String responseStr)
//    {
//        if (code == 401)
//        {
//            if (context instanceof LoginActivity)
//            {
//                Toast.makeText(context,"用户名或密码错误!",Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                Intent intent = new Intent(context, LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("autoLogin", false);
//                context.startActivity(intent);
//            }
//            return true;
//        }
//        else if (code == 200)
//        {
//            BaseResponse response = JSON.parseObject(responseStr, BaseResponse.class);
//            if (response.code == ErrorCode.ACCOUNT_LOCKED || response.code == ErrorCode.ACCOUNT_FROZEN
//                || response.code == ErrorCode.TOKEN_EXPIRE)
//            {
//                Toast.makeText(context,response.desc,Toast.LENGTH_SHORT).show();
//                if (!(context instanceof LoginActivity))
//                {
//                    Intent intent = new Intent(context, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("autoLogin", false);
//                    context.startActivity(intent);
//                }
//            }
//        }
//        return false;
//    }
    
    @Override
    public void onStart()
    {
//        if (!TextUtils.isEmpty(mMsg))
//        {
//            DialogUtils.showProgressDialog(mContext, mMsg, false);
//        }
        if (null != mHandler && null != mContext)
        {
            mHandler.onStart();
        }
    }
    
    @Override
    public void onFinish()
    {
//        if (!TextUtils.isEmpty(mMsg))
//        {
//            DialogUtils.dismissDialog();
//        }
        if (null != mHandler && null != mContext)
        {
            mHandler.onFinish();
        }
    }
    
    @Override
    public void onSuccess(int code, String result)
    {
        if(BuildConfig.ENABLE_DEBUG)
        {
            Log.i("onSuccess", "Http onSuccess:" + result);
        }
        if (null != mHandler && null != mContext)
        {
            
            mHandler.onSuccess(code, result, DigestData.getBaseResponse(result));
        }
    }
    
    @Override
    public void onFailed()
    {
        if(BuildConfig.ENABLE_DEBUG)
        {
            Log.e("onFailed", "Http onFailed");
        }
        if (null != mHandler && null != mContext)
        {
            mHandler.onFailure();
        }
    }
}
