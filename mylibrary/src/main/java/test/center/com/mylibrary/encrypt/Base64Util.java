package test.center.com.mylibrary.encrypt;

import java.io.UnsupportedEncodingException;

/**
 * Base64加、解码工具类.
 *
 * @author HC
 * @version 1.0.0
 */
public class Base64Util
{
    /**
     * @param base64 字符串
     * @return 二进制
     * @desc <p>
     * BASE64字符串解码为二进制数据
     * </p>
     * .
     * @author HC
     * @date Jun 19, 2013
     */
    public static byte[] decode(String base64)
    {
        /**换成Android本地BASE64类进行**/
        return Base64.decode(base64, Base64.DEFAULT);
    }
    
    /**
     * @param bytes 二进制
     * @return 字符串
     * @desc <p>
     * 二进制数据编码为BASE64字符串
     * </p>
     * .
     * @author HC
     * @date Jun 19, 2013
     */
    public static String encode(byte[] bytes)
    {
        /**换成Android本地BASE64类进行**/
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
    
    /**
     * @param base64 编码字符串
     * @return String
     * @desc 解码并转为String.
     * @author HC
     * @date Jul 8, 2013
     */
    public static String decode2Str(String base64)
    {
        try
        {
            return new String(decode(base64), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
