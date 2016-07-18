package test.center.com.mylibrary.encrypt;

import test.center.com.mylibrary.jni.IotJniUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESTools
{
    private static final String EMPTY = "";
    
    private static final byte[] KEY_ARRAY =
        {35, 110, -87, -15, -5, 39, -62, -22, -98, 66, 110, -50, -110, -25, -24, -30};
    
    /**
     * AES加密，本地加密
     * 功能详细描述
     *
     * @param content 需要加密的内容
     * @return byte[] 加密结果
     */
    public static byte[] encryptLocal(String content)
    {
        KeyGenerator kgen;
        try
        {
            if (content == null)
            {
                return null;
            }
            //生成加密密钥（通过对特定数据的MD5摘要获得）
            MessageDigest digest = MessageDigest.getInstance("MD5");
            SecretKeySpec key = new SecretKeySpec(digest.digest(IotJniUtils.getLocalKeys().getBytes()), "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            
            byte[] byteContent = content.getBytes("utf-8");
            
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            byte[] result = cipher.doFinal(byteContent);
            
            // 加密
            return result;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * AES解密，用于本地解密
     * 功能详细描述
     *
     * @param content 待解密内容
     * @return String 解密结果
     */
    public static String decryptLocal(byte[] content)
    {
        try
        {
            //生成加密密钥（通过对特定数据的MD5摘要获得）
            MessageDigest digest = MessageDigest.getInstance("MD5");
            SecretKeySpec key = new SecretKeySpec(digest.digest(IotJniUtils.getLocalKeys().getBytes()), "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            // 解密
            return new String(result, "utf-8");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * AES加密
     * 功能详细描述
     *
     * @param content 需要加密的内容
     * @return String 加密结果
     */
    public static String encrypt(String content)
    {
        KeyGenerator kgen;
        try
        {
            SecretKeySpec key = new SecretKeySpec(KEY_ARRAY, "AES");
            
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            
            byte[] byteContent = content.getBytes("utf-8");
            
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            byte[] result = cipher.doFinal(byteContent);
            
            // 加密
            return parseByte2HexStr(result);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * AES加密
     * 功能详细描述
     *
     * @param content  需要加密的内容
     * @param password 加密密钥
     * @return String 加密结果
     */
    public static String encrypt(String content, String password)
    {
        try
        {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            
            secureRandom.setSeed(password.getBytes());
            
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            
            kgen.init(128, secureRandom);
            
            SecretKeySpec key = new SecretKeySpec(KEY_ARRAY, "AES");
            
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            
            byte[] byteContent = content.getBytes("utf-8");
            
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            byte[] result = cipher.doFinal(byteContent);
            
            // 加密
            return parseByte2HexStr(result);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * AES解密
     * 功能详细描述
     *
     * @param content 待解密内容
     * @return String 解密结果
     */
    public static String decrypt(String content)
    {
        try
        {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            
            secureRandom.setSeed(EMPTY.getBytes());
            
            byte[] decryptFrom = parseHexStr2Byte(content);
            
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            
            kgen.init(128, secureRandom);
            
            SecretKey secretKey = kgen.generateKey();
            
            byte[] enCodeFormat = secretKey.getEncoded();
            
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, key);
            
            byte[] result = cipher.doFinal(decryptFrom);
            
            // 解密
            return new String(result);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * AES解密
     * 功能详细描述>
     *
     * @param content 待解密内容
     * @return String 解密结果
     */
    public static String decrypt(String content, byte[] keys)
    {
        try
        {
            byte[] decryptFrom = parseHexStr2Byte(content);
            
            SecretKeySpec key = new SecretKeySpec(keys, "AES");
            
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, key);
            
            byte[] result = cipher.doFinal(decryptFrom);
            
            // 解密
            return new String(result);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将二进制转换成16进制
     *
     * @param buf 待转换字符数组
     * @return 转换后字符串
     */
    public static String parseByte2HexStr(byte[] buf)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++)
        {
            try
            {
                String hex = Integer.toHexString(buf[i] & 0xFF);
                if (hex.length() == 1)
                {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    
    /**
     * 将16进制转换为二进制
     *
     * @param hexStr 待转换16进制数组
     * @return 转换后二进制数组
     */
    public static byte[] parseHexStr2Byte(String hexStr)
    {
        if (hexStr.length() < 1)
        {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++)
        {
            try
            {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte)(high * 16 + low);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
        }
        return result;
    }
    
}
