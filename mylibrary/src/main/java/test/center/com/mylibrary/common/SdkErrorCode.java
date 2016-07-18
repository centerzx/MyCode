package test.center.com.mylibrary.common;

/**
 * Created by MaJian on 2016/4/22.
 */
public class SdkErrorCode
{
    //sdk error 
    public static final int CORE_ERR_UNKNOWN = -1;
    
    public static final int CORE_OK = 0;
    
    public static final int CORE_DOMAIN_ERROR = 6;
    
    public static final int CORE_ERR_NOMEMORY = 100;
    
    public static final int CORE_ERR_INVALID_PARAM = 101;
    
    public static final int CORE_ERR_INTERNAL = 102;
    
    public static final int CORE_ERR_NETWORK = 103;
    
    public static final int CORE_ERR_CANCELED = 104;
    
    public static final int CORE_ERR_CLIENTID = 200;
    
    public static final int CORE_ERR_TOKEN = 201;
    
    public static final int CORE_ERR_API_SERVER = 202;
    
    public static final int CORE_ERR_DEVICEID = 203;
    
    public static final int CORE_ERR_PAGESIZE = 204;
    
    public static final int CORE_ERR_INVALID_EMAIL = 205;
    
    public static final int CORE_ERR_NAME_LENGTH = 206;
    
    public static final int CORE_ERR_INVALID_NAME = 207;
    
    public static final int CORE_ERR_FILENAME_LENGTH = 208;
    
    public static final int CORE_ERR_INVALID_FILENAME = 209;
    
    public static final int CORE_ERR_PASSWORD_LENGTH = 210;
    
    public static final int CORE_ERR_INVALID_PASSWORD = 211;
    
    public static final int CORE_ERR_DOWNLOAD_FAILED = 212;
    
    public static final int CORE_ERR_DOWNLOAD_NOT_FINISHED = 213;
    
    public static final int CORE_ERR_FILE = 214;
    
    //server return code
    public static final int CORE_RC_INVALID_TOKEN = 1000;
    
    public static final int CORE_RC_INVALID_CLIENT_ID = 1002;
    
    public static final int CORE_RC_INVALID_SIGNATURE = 1003;
    
    public static final int CORE_RC_USER_NOT_EXIST = 1105;
    
    public static final int CORE_RC_INVALID_METHOD = 1101;
    
    public static final int CORE_RC_NETWORK_ERROR = 1111;
    
    public static final int CORE_RC_PASSWORD_WRONG = 1201;
    
    public static final int CORE_RC_USER_DISABLED = 1202;
    
    public static final int CORE_RC_INVALID_FILE = 1331;
    
    public static final int CORE_RC_INVALID_PATH = 1332;
    
    public static final int CORE_RC_INVALID_FILEID = 1333;
    
    public static final int CORE_RC_INVALID_TIME = 1335;
    
    public static final int CORE_RC_INVALID_FILE_TYPE = 1336;
    
    public static final int CORE_RC_UPLOAD_FAILED = 1337;
    
    public static final int CORE_RC_INVALID_ID = 1350;
    
    public static final int CORE_RC_INVALID_TARGET_FOLDER = 1352;
    
    public static final int CORE_RC_INVALID_FILE_LENGTH = 1361;
    
    public static final int CORE_RC_INVALID_BLOCK_NUMBER = 1362;
    
    public static final int CORE_RC_INVALID_UUID = 1363;
    
    public static final int CORE_RC_INVALID_DEVICE_ID = 1401;
    
    public static final int CORE_RC_INVALID_DEVICE_NAME = 1402;
    
    public static final int CORE_RC_INVALID_PAGE = 1407;
    
    public static final int CORE_RC_NO_SPACE = 1432;
    
    public static final int CORE_RC_DEVICENAME_EXIST = 1433;
    
    public static final int CORE_RC_FILE_UPDATED = 1435;
    
    public static final int CORE_RC_CANNOT_SHARE_TO_YOUSELF = 1447;
    
    public static final int CORE_RC_INVALID_SHARE_FLAG = 1448;
    
    public static final int CORE_RC_INVALID_SHARE_ITEM_ID = 1455;
    
    public static final int CORE_RC_SHARED_FOLDER_NOT_AVAILABLE = 1457;
    
    public static final int CORE_RC_NEWFILENAME_EXIST = 1458;
    
    public static final int CORE_RC_INVALID_NEWFILENAME = 1459;
    
    public static final int CORE_RC_INVALID_UPS = 1461;
    
    public static final int CORE_RC_FILENAME_EXIST = 1466;
    
    public static final int CORE_RC_INVALID_FILEVERSION = 1468;
    
    public static final int CORE_RC_OWNERSPACE_USEDUP = 1472;
    
    public static final int CORE_RC_PRIVILEGE_ERROR = 1474;
    
    public static final int CORE_RC_INVALID_ITEMID = 1462;
    
    public static final int CORE_RC_UPS_NOT_AVAILABLE = 1465;
    
    public static final int CORE_RC_STORAGE_USED_UP = 1472;
    
    public static final int CORE_RC_INVALID_VERSION = 1535;
    
    public static final int CORE_RC_INVALID_BLOCK_INDEX = 1602;
    
    public static final int CORE_RC_SERVER_ERROR = 9000;
    
    public static final int CORE_RC_INVALID_REFLESHTOKEN = 6003;
    
    public static final int CORE_RC_INVALID_SCOPE = 6006;
    
    public static final int CORE_RC_INVALID_ACCESSTOKEN = 6007;
    
    //Auth error
    public static final int CORE_RC_SERVICE_EXCEPTION = 10001;
    
    public static final int CORE_RC_BLANK_USERNAME = 10002;
    
    public static final int CORE_RC_USERNAME_NOT_EXISTS = 10003;
    
    public static final int CORE_RC_INVALID_USER_NAME = 10004;
    
    public static final int CORE_RC_PASSWORD_NOT_CORRECT = 10005;
    
    public static final int CORE_RC_USER_LOCKED = 10006;
    
    public static final int CORE_RC_AUTH_USER_DISABLED = 10007;
    
    public static final int CORE_RC_ACCOUNT_EXPIRED = 10008;
    
    public static final int CORE_RC_PASSWORD_EXPIRED = 10009;
    
    public static final int CORE_RC_AUTH_INVALID_SIGNATURE = 10010;
    
    public static final int CORE_RC_ACCOUNT_UNACTIVATED = 10011;
    
    public static final int CORE_RC_LOGIN_ERROR_MANY_TIMES = 10012;
    
    public static final int CORE_RC_SYSTEM_ERROR = 11001;
    
    public static final int CORE_RC_BLANK_PASSWORD = 11003;
    
    public static final int CORE_RC_BLANK_EMAIL = 11004;
    
    public static final int CORE_RC_PASSWORD_TOO_SHORT = 11008;
    
    public static final int CORE_RC_PASSWORD_TOO_LONG = 11009;
    
    public static final int CORE_RC_INVALID_PASSWORD = 11010;
    
    public static final int CORE_RC_INVALID_EMAIL = 11011;
    
    public static final int CORE_RC_EMAIL_EXISTD = 11013;
    
    public static final int CORE_RC_EMAIL_AND_UNIFIED_BOTH_BLANK = 11014;
    
    public static final int CORE_RC_INVALID_CHECK_CODE = 11204;
    
    public static final int CORE_RC_EMAIL_NOT_EXISTS = 11205;
    
    public static final int CORE_RC_BLANK_USER_ID = 11214;
    
    public static final int CORE_RC_ACCOUNT_ACTIVATED = 11226;
    
    public static final int CORE_RC_MOBILE_REGISTERED = 11307;
    
    public static final int CORE_RC_CHECK_CODE_VERIFY_ERROR = 11600;
    
    public static final int CORE_RC_CHECK_CODE_VERIFY_TOO_MUCH = 11601;
    
    public static final int CORE_RC_CHECK_CODE_EXPIRED = 11602;
    
    public static final int CORE_RC_MOBILE_USER_EXIST = 11603;
    
    public static final int CORE_RC_MOBILE_USER_NOT_EXIST = 11604;
    
    public static final int CORE_RC_SEND_CHECK_CODE_ERROR = 11605;
    
    public static final int CORE_RC_SEND_CHECK_CODE_FREQUENTLY = 11606;
    
    public static final int CORE_RC_SEND_CHECK_CODE_TYPE_INVALID = 11607;
    
    public static final int CORE_RC_MOBILE_BLANK = 11608;
    
    public static final int CORE_RC_MOBILE_INVALID = 11609;
    
    public static final int CORE_RC_TYPE_BLANK = 11610;
    
    public static final int CORE_RC_INVALID_REQUEST = 16001;
    
    public static final int CORE_RC_INVALID_CLIENT = 16002;
    
    public static final int CORE_RC_INVALID_GRANT = 16003;
    
    public static final int CORE_RC_UNAUTHORIZATION_CLIENT = 16004;
    
    public static final int CORE_RC_UNAUTHORIZATION_GRANT = 16005;
    
    public static final int CORE_RC_AUTH_INVALID_SCOPE = 16006;
    
    public static final int CORE_RC_AUTH_INVALID_TOKEN = 16007;
    
    public static final int CORE_RC_AUTH_INVALID_RESPONSE = 16009;
    
    public static final int CORE_RC_ACCESS_DENIED = 16010;
    
    public static final int CORE_RC_CLIP_DURATION_USED_UP = 20041;
    
    public static final int CORE_RC_FILE_NOT_FOUND = 30001;
    
}
