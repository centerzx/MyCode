package test.center.com.mylibrary.http;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import test.center.com.mylibrary.common.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by MaJian on 2015/12/17.
 */
public class IotHttpClient
{
    private static ExecutorService mExecutorService = Executors.newFixedThreadPool(5);
    
    private static Handler mHandler = new Handler();
    
    /**
     * @param act
     * @param params  请求参数
     * @param msg     请求过程中显示Dialog
     * @param handler 请求结果接收
     */
    private static void postAuth(final Activity act, String userName, String userPass, JSONObject params, final 
    String msg, final HttpResponseHandler handler)
    {
        if (act == null)
        {
            return;
        }
        addParams(params);
        Log.i("Http postAuth", "Http Post:" + params.toString());
        HttpRequestTask task = new HttpRequestTask(mHandler, userName, userPass, params.toString().getBytes(),
            new IotHttpStatusListener(act, msg, handler));
        executeTask(task);
    }
    
    private static void postNoAuth(final Context act, JSONObject params, final String msg, final HttpResponseHandler 
        handler)
    {
        if (act == null)
        {
            return;
        }
        addParams(params);
        Log.i("Http postNoAuth", "Http Post:" + params.toString());
        HttpRequestTask task =
            new HttpRequestTask(mHandler, params.toString().getBytes(), new IotHttpStatusListener(act, msg, handler));
        executeTask(task);
    }
    
    private static void executeTask(HttpRequestTask task)
    {
        if (mExecutorService.isShutdown())
        {
            mExecutorService = Executors.newFixedThreadPool(5);
        }
        mExecutorService.execute(task);
    }
    
    /**
     * @param context
     * @param params
     * @param handler
     */
    private static void postAuth(Activity context, String userName, String userPass, JSONObject params, final 
    HttpResponseHandler handler)
    {
        postAuth(context, userName, userPass, params, null, handler);
    }
    
    private static void postNoAuth(Activity activity, JSONObject params, final HttpResponseHandler handler)
    {
        postNoAuth(activity, params, null, handler);
    }
    
    private static JSONObject addParams(JSONObject params)
    {
        try
        {
            params.put("version", Constants.VERSION);
            params.put("msgSeq", Constants.MSG_SEQ);
            params.put("clientType", Constants.CLIENT_TYPE);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return params;
    }
    
    public static void cancelAll()
    {
        mExecutorService.shutdownNow();
    }
    
    /**
     * 用户登录
     * 由于要鉴权，所以需传入密码
     * 获取的回调内容可以自己解析Json格式的result
     *
     * @param context
     * @param userName
     * @param userPass
     * @param handler
     */
    public static void Login(Activity context, String userName, String userPass, final HttpResponseHandler handler)
    {
        try
        {
            JSONObject params = new JSONObject();
            params.put("msgType", HttpCmds.MSG_GET_USERINFO_REQ);
            params.put("phoneNo", userName);
            postAuth(context, userName, userPass, params, handler);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 用户注册
     *
     * @param context
     * @param userName 手机号
     * @param userPass 密码（需要MD5加密，再用AES和BASE64）
     * @param code     获取的手机验证码
     * @param handler  回调
     */
    public static void Register(Activity context, String userName, String userPass, String code, final 
    HttpResponseHandler handler)
    {
        try
        {
            JSONObject params = new JSONObject();
            params.put("msgType", HttpCmds.MSG_SIGN_UP_REQ);
            params.put("phoneNo", userName);
            params.put("userName", "H" + userName);
            params.put("msgCode", code);
            params.put("password", userPass);
            postNoAuth(context, params, handler);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取手机验证码
     *
     * @param context
     * @param userName 手机号
     * @param handler
     */
    public static void getPhoneVerificationCode(Activity context, String userName, final HttpResponseHandler handler)
    {
        try
        {
            JSONObject params = new JSONObject();
            params.put("msgType", HttpCmds.MSG_GET_SMS_REQ);
            params.put("phoneNo", userName);
            postNoAuth(context, params, handler);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 验证手机号是否注册
     * 返回值isRegistered为1表示已注册
     *
     * @param context
     * @param userName 手机号
     * @param handler
     */
    public static void getUserIsExist(Activity context, String userName, final HttpResponseHandler handler)
    {
        try
        {
            JSONObject params = new JSONObject();
            params.put("msgType", HttpCmds.MSG_IS_USER_REGISTERED_REQ);
            params.put("userName", "H" + userName);
            postNoAuth(context, params, handler);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 忘记密码，进行密码重置
     *
     * @param context
     * @param userName 手机号
     * @param userPass 密码（需要MD5加密，再用AES和BASE64）
     * @param code     获取的手机验证码
     * @param handler  回调
     */
    public static void resetPassWord(Activity context, String userName, String userPass, String code, final 
    HttpResponseHandler handler)
    {
        try
        {
            JSONObject params = new JSONObject();
            params.put("msgType", HttpCmds.MSG_RESET_PASSWORD_REQ);
            params.put("phoneNo", userName);
            params.put("password", userPass);
            params.put("msgCode", code);
            postNoAuth(context, params, handler);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 用户更改密码
     * 由于要鉴权，需传入用户名和密码
     *
     * @param context
     * @param userName 用户名（手机号）
     * @param userId   用户ID
     * @param oldPass  原密码（需要MD5加密，再用AES和BASE64）
     * @param newPass  新密码（需要MD5加密，再用AES和BASE64）
     * @param handler
     */
    public static void changePassWord(Activity context, String userName, String userId, String oldPass, String 
        newPass, final HttpResponseHandler handler)
    {
        try
        {
            JSONObject params = new JSONObject();
            params.put("userID", userId);
            params.put("oldPassword", oldPass);
            params.put("newPassword", newPass);
            params.put("msgType", HttpCmds.MSG_CHANGE_PASSWORD_REQ);
            postAuth(context, userName, oldPass, params, handler);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
