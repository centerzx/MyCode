package test.center.com.mylibrary.http;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import test.center.com.mylibrary.encrypt.DigestUtils;
import test.center.com.mylibrary.jni.IotJniUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MaJian on 2015/12/17.
 */
public class HttpRequestTask implements Runnable
{
    private byte[] mData;
    
    private HttpTaskListener mListener;
    
    private String mUserName;
    
    private String mUserPass;
    
    private Handler mHandler;
    
    public HttpRequestTask(Handler handler, String userName, String userPass, byte[] data, HttpTaskListener listener)
    {
        mHandler = handler;
        mUserName = userName;
        mUserPass = userPass;
        mData = data;
        mListener = listener;
    }
    
    public HttpRequestTask(Handler handler, byte[] data, HttpTaskListener listener)
    {
        mHandler = handler;
        mData = data;
        mListener = listener;
    }
    
    private HttpURLConnection createConn()
    {
        HttpURLConnection connection = null;
        try
        {
            if (!TextUtils.isEmpty(mUserName) && !TextUtils.isEmpty(mUserPass))
            {
                connection = HttpDigestAuth.tryAuth(IotJniUtils.getUrlString(1), mUserName, DigestUtils.md5(mUserPass));
            }
            else
            {
                connection = (HttpURLConnection)new URL(IotJniUtils.getUrlString(0)).openConnection();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return connection;
        
    }
    
    private void requestStart()
    {
        if (null != mListener)
        {
            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    mListener.onStart();
                }
            });
            
        }
    }
    
    private void requestFinish()
    {
        if (null != mListener)
        {
            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    mListener.onFinish();
                }
            });
            
        }
    }
    
    private void requestSuccess(final int code, final String content)
    {
        if (mListener != null)
        {
            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    mListener.onSuccess(code, content);
                }
            });
        }
    }
    
    private void requestFailed()
    {
        if (mListener != null)
        {
            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    mListener.onFailed();
                }
            });
        }
    }
    
    @Override
    public void run()
    {
        requestStart();
        HttpURLConnection httpConn = null;
        OutputStream out = null;
        InputStream in = null;
        BufferedReader reader = null;
        try
        {
            httpConn = createConn();
            if (httpConn == null)
            {
                requestFailed();
                return;
            }
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            httpConn.connect();
            out = httpConn.getOutputStream();
            out.write(mData);
            out.flush();
            int code = httpConn.getResponseCode();
            if (code == ResultCode.SUCCESS)
            {
                in = httpConn.getInputStream();
            }
            else
            {
                in = httpConn.getErrorStream();
            }
            reader = new BufferedReader(new InputStreamReader(in));
            String temp = null;
            StringBuilder content = new StringBuilder();
            while ((temp = reader.readLine()) != null)
            {
                content.append(temp);
            }
            requestSuccess(code, content.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            
            requestFailed();
        }
        finally
        {
            requestFinish();
            if (null != httpConn)
            {
                httpConn.disconnect();
            }
            try
            {
                if (null != out)
                {
                    out.close();
                }
                if (null != in)
                {
                    in.close();
                }
                if (null != reader)
                {
                    reader.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
