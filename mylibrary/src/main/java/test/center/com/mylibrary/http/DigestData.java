/*
 * 文 件 名:  DigestData.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014年4月10日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package test.center.com.mylibrary.http;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MaJian on 2015/12/17.
 */
public class DigestData
{
    private String username = "admin";
    
    private String password = "admin";
    
    private String realm;
    
    private String qop;
    
    private String nonce;
    
    private String opaque;
    
    private String algorithm;
    
    private String uri;
    
    private String cnonce;
    
    /**
     * <默认构造函数>
     */
    public DigestData()
    {
        
    }
    
    public DigestData(String realm, String qop, String nonce, String opaque)
    {
        this.realm = realm;
        this.qop = qop;
        this.nonce = nonce;
        this.opaque = opaque;
        algorithm = "MD5";
    }
    
    public String getAlgorithm()
    {
        return algorithm;
    }
    
    public void setAlgorithm(String a)
    {
        algorithm = a;
    }
    
    /**
     * @return 返回 username
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * @param username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    /**
     * @return 返回 password
     */
    public String getPassword()
    {
        return password;
    }
    
    /**
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    /**
     * @return 返回 realm
     */
    public String getRealm()
    {
        return realm;
    }
    
    /**
     * @param realm
     */
    public void setRealm(String realm)
    {
        this.realm = realm;
    }
    
    /**
     * @return 返回 qop
     */
    public String getQop()
    {
        return qop;
    }
    
    /**
     * @param qop
     */
    public void setQop(String qop)
    {
        this.qop = qop;
    }
    
    /**
     * @return 返回 nonce
     */
    public String getNonce()
    {
        return nonce;
    }
    
    /**
     * @param nonce
     */
    public void setNonce(String nonce)
    {
        this.nonce = nonce;
    }
    
    /**
     * @return 返回 opaque
     */
    public String getOpaque()
    {
        return opaque;
    }
    
    /**
     * @param opaque
     */
    public void setOpaque(String opaque)
    {
        this.opaque = opaque;
    }
    
    /**
     * @return 返回 uri
     */
    public String getUri()
    {
        return uri;
    }
    
    /**
     * @param uri
     */
    public void setUri(String uri)
    {
        this.uri = uri;
    }
    
    /**
     * @return 返回 cnonce
     */
    public String getCnonce()
    {
        return cnonce;
    }
    
    /**
     * @param cnonce
     */
    public void setCnonce(String cnonce)
    {
        this.cnonce = cnonce;
    }
    
    public static BaseResponse getBaseResponse(String result)
    {
        BaseResponse baseResponse = new BaseResponse();
        try
        {
            JSONObject jsonObject = new JSONObject(result);
            baseResponse.code = jsonObject.optInt("errorCode");
            baseResponse.version = jsonObject.optInt("version");
            baseResponse.desc = jsonObject.optString("description");
            baseResponse.msgType = jsonObject.optString("msgType");
            baseResponse.msgSeq = jsonObject.optString("msgSeq");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return baseResponse;
    }
    
}
