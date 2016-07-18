package test.center.com.mylibrary.encrypt;

import android.text.TextUtils;

public class SecurityUtils
{
    /**
     * @author WonderTek
     * @password need MD5
     */
    public static String encript(String pwd)
    {
        if (TextUtils.isEmpty(pwd))
        {
            return pwd;
        }
        
        String aes1 = AESTools.encrypt(DigestUtils.md5(pwd));
        String base64 = Base64Util.encode(aes1.getBytes());
        int iSel = base64.lastIndexOf('\n');
        int iLen = base64.length();
        if ((iSel > 0) && (iSel == (iLen - 1)))
        {
            base64 = base64.substring(0, iSel).trim();
        }
        return base64;
    }
    
    /**
     * @param pwd without MD5
     * @return
     * @author WonderTek
     */
    public static String encriptWithOutMD5(String pwd)
    {
        if (TextUtils.isEmpty(pwd))
        {
            return pwd;
        }
        
        String aes1 = AESTools.encrypt(pwd);
        String base64 = Base64Util.encode(aes1.getBytes());
        int iSel = base64.lastIndexOf('\n');
        int iLen = base64.length();
        if ((iSel > 0) && (iSel == (iLen - 1)))
        {
            base64 = base64.substring(0, iSel).trim();
        }
        return base64;
    }
    
    /**
     * @param pwd Local
     * @return
     */
    public static String encriptLocal(String pwd)
    {
        if (TextUtils.isEmpty(pwd))
        {
            return pwd;
        }
        
        byte[] aesBytes = AESTools.encryptLocal(pwd);
        String base64 = Base64Util.encode(aesBytes);
        base64 = base64.replace("\n", " ").trim();
        int iSel = base64.lastIndexOf('\n');
        int iLen = base64.length();
        if ((iSel > 0) && (iSel == (iLen - 1)))
        {
            base64 = base64.substring(0, iSel).trim();
        }
        return base64;
    }
    
    /**
     * @param content decode
     * @return
     */
    public static String decriptLocal(String content)
    {
        if (TextUtils.isEmpty(content))
        {
            return content;
        }
        byte[] contentByte = Base64Util.decode(content);
        return AESTools.decryptLocal(contentByte);
    }
    
}
