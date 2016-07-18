package test.center.com.mylibrary.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MaJian on 2015/12/17.
 */
public class HttpDigestAuth
{
    private static final String HEX_LOOKUP = "0123456789abcdef";
    
    public static HttpURLConnection tryAuth(String url, String username, String password) throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED)
        {
            connection = tryDigestAuthentication(connection, username, password);
            if (connection == null)
            {
                throw new AuthenticationException();
            }
        }
        return connection;
    }
    
    public static HttpURLConnection tryDigestAuthentication(HttpURLConnection connection, String username, String 
        password)
    {
        String auth = connection.getHeaderField("WWW-Authenticate");
        String url = connection.getURL().toString();
        if (auth == null || !auth.startsWith("Digest "))
        {
            return null;
        }
        final Map<String, String> authFields = splitAuthFields(auth.substring(7));
        
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            return null;
        }
        
        String ha1 = null;
        try
        {
            md5.reset();
            String ha1str = username + ":" + authFields.get("realm") + ":" + password;
            md5.update(ha1str.getBytes("ISO-8859-1"));
            byte[] ha1bytes = md5.digest();
            ha1 = bytesToHexString(ha1bytes);
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
        
        String ha2 = null;
        try
        {
            md5.reset();
            String ha2str = "POST:" + url;
            md5.update(ha2str.getBytes("ISO-8859-1"));
            ha2 = bytesToHexString(md5.digest());
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
        
        String ha3 = null;
        try
        {
            md5.reset();
            String ha3str = ha1 + ":" + authFields.get("nonce") + ":" + authFields.get("nonce") + ":smarthome_client:" +
                authFields.get("qop") + ":" + ha2;
            md5.update(ha3str.getBytes("ISO-8859-1"));
            ha3 = bytesToHexString(md5.digest());
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
        
        StringBuilder sb = new StringBuilder(128);
        sb.append("Digest ");
        sb.append("username=\"").append(username).append("\",");
        sb.append("realm=\"").append(authFields.get("realm")).append("\",");
        sb.append("nonce=\"").append(authFields.get("nonce")).append("\",");
        sb.append("uri=\"").append(url).append("\",");
        sb.append("algorithm=\"MD5\",");
        sb.append("qop=\"auth\",");
        sb.append("nc=\"00000002\",");
        sb.append("cnonce=\"smarthome_client\",");
        sb.append("response=\"").append(ha3).append("\",");
        sb.append("opaque=\"").append(authFields.get("opaque")).append('\"');
        
        try
        {
            final HttpURLConnection result = (HttpURLConnection)connection.getURL().openConnection();
            result.addRequestProperty("Authorization", sb.toString());
            return result;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    private static Map<String, String> splitAuthFields(String authString)
    {
        final HashMap<String, String> fields = new HashMap<>();
        String[] kv = null;
        for (String keyPair : authString.split(","))
        {
            kv = keyPair.split("=");
            fields.put(kv[0].trim(), kv[1].replaceAll("\"", "").trim());
        }
        return fields;
    }
    
    private static String bytesToHexString(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++)
        {
            sb.append(HEX_LOOKUP.charAt((bytes[i] & 0xF0) >> 4));
            sb.append(HEX_LOOKUP.charAt((bytes[i] & 0x0F) >> 0));
        }
        return sb.toString();
    }
    
    public static class AuthenticationException extends IOException
    {
        private static final long serialVersionUID = 1L;
        
        public AuthenticationException()
        {
            super("Problems authenticating");
        }
    }
}
