package com.center.mycode.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.cmcc.andmu.iothttpsdk.http.BaseResponse;

/**
 * Created by MaJian on 2015/12/18.
 */
public class UserInfoResponse extends BaseResponse
{
    @JSONField(name = "userID")
    public String userId;
}
