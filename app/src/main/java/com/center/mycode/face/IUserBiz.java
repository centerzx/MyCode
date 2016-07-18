package com.center.mycode.face;

import com.center.mycode.bean.User;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/5/18.
 */
public interface IUserBiz<T>
{
    /**
     *直接请求
     */
    @GET("3c3300a9e83a")
    Call<User> getUsers();
    
    @POST("{username}")
    Call getUser(@Path("username") String username);
    
    /**
     * 参数选择通过@Query来设置
     * http://baseurl/users?sortby=username       http://baseurl/users?sortby=id
     */
    @POST("users")
    Call<User> getUsersBySort(@Query("sortby") String sort);
    
    /**
     * POST方式将json字符串作为请求体发送到服务器
     */
    @POST("add")
    Call<User> addUser(@Body User user);
    
    /**
     * 表单的方式传递键值对@FormUrlEncoded
     */
    @POST("login")
    @FormUrlEncoded
    Call login(@Field("username") String username, @Field("password") String password);
    
    /**
     * 单文件上传@Multipart,使用如下
     * File file = new File(Environment.getExternalStorageDirectory(), "icon.png");
     * RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
     * MultipartBody.Part photo = MultipartBody.Part.createFormData("photos", "icon.png", photoRequestBody);
     * Call call = userBiz.registerUser(photo, RequestBody.create(null, "abc"), RequestBody.create(null, "123"))
     */
    @Multipart
    @POST("register")
    Call registerUser(@Part MultipartBody.Part photo, @Part("username") RequestBody username, @Part("password") 
    RequestBody password);
    
    @Multipart
    @POST("register")
    Call<User> registerUser(@PartMap Map<String, RequestBody> params, @Part("password") RequestBody password);
    
}
