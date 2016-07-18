package test.center.com.mylibrary.http;

/**
 * Created by MaJian on 2015/12/17.
 */
public abstract class HttpResponseHandler
{
    public Class<BaseResponse> clazz;
    
    public HttpResponseHandler(Class c)
    {
        clazz = c;
    }
    
    public void onStart()
    {
        
    }
    
    public void onFinish()
    {
        
    }
    
    public abstract void onSuccess(int code, String result, BaseResponse response);
    
    public abstract void onFailure();
}
