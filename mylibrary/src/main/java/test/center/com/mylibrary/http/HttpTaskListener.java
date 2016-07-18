package test.center.com.mylibrary.http;

/**
 * Created by MaJian on 2015/12/18.
 */
public interface HttpTaskListener
{
    void onStart();
    
    void onFinish();
    
    void onSuccess(int code, String result);
    
    void onFailed();
}
