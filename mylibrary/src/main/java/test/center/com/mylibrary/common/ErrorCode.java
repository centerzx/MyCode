package test.center.com.mylibrary.common;

/**
 * Created by MaJian on 2016/2/1.
 */
public class ErrorCode
{
    //成功
    public static final int SUCCESS = 0x0000;
    
    //服务器内部错误
    public static final int SERVER_ERROR = 0x0001;
    
    //消息格式错误
    public static final int FORMAT_ERROR = 0x0002;
    
    //请求参数错误
    public static final int PARAMS_ERROR = 0x0003;
    
    //无操作权限
    public static final int NO_PERMISSION = 0x0004;
    
    //该手机已被注册
    public static final int PHONE_ALREADY_REGISTER = 0x1001;
    
    //用户名或密码错误
    public static final int USERNAME_PWD_ERROR = 0x1002;
    
    //旧密码不匹配
    public static final int OLD_PWD_ERROR = 0x1003;
    
    //用户账号不存在
    public static final int ACCOUNT_NOT_EXIST = 0x1004;
    
    //设备已被绑定
    public static final int DEVICE_ALREADY_BOUND = 0x1005;
    
    //该用户未绑定设备
    public static final int USER_NOT_BOUND_DEVICE = 0x1006;
    
    //该用户账号已被锁定
    public static final int ACCOUNT_LOCKED = 0x1007;
    
    //用户Token已过期
    public static final int TOKEN_EXPIRE = 0x1010;
    
    //该用户账号已被冻结
    public static final int ACCOUNT_FROZEN = 0x1013;
    
    //短信验证码过期
    public static final int VALI_CODE_TIMEOUT = 8196;
}
