package test.center.com.mylibrary.jni;

/**
 * Created by Administrator on 2016/7/1.
 */
public class IotJniUtils
{
    
    static
    {
        System.loadLibrary("mylibrary");
    }
    
    public static native String getUrlString(int type);
    
    public static native String getLocalKeys();
    
    public static native String getAccessSecrey();
}
