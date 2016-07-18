package test.center.com.mylibrary.encrypt;

import android.text.TextUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MaJian on 2015/11/19.
 */
public class DigestUtils
{
    private static final char[] DIGITS_LOWER =
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    
    private static final char[] DIGITS_UPPER =
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    
    public static String md5Hex(final String data)
    {
        if (null == data)
        {
            return null;
        }
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance("md5");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
        return encodeHexString(digest.digest(data.getBytes()));
    }
    
    private static String encodeHexString(byte[] data)
    {
        final int l = data.length;
        final char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++)
        {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return new String(out);
    }
    
    public static String md5(String text)
    {
        MessageDigest digest;
        String result = "";
        if (TextUtils.isEmpty(text))
        {
            return result;
        }
        try
        {
            digest = MessageDigest.getInstance("MD5");
            digest.update(text.getBytes());
            result = new BigInteger(1, digest.digest()).toString(16);
            
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
