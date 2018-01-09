package com.example.pp03.httputils;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.example.pp03.httputils.HttpUtils.BaseReq;
import com.example.pp03.httputils.HttpUtils.JsonParseUtil;

/**
 * Created by pp03 on 2018-01-09.
 */

public class LoginBaseResq extends BaseReq{
    Bean bean;
    public LoginBaseResq(String username,String pwd){
        paramsMap.put("username",username);
        paramsMap.put("password",pwd);

    }
    @Override
    public String getInterfaceCtype() {
        return "Welcome";
    }

    @Override
    public String getInterfaceMtype() {
        return "login";
    }

    @Override
    protected void parseResponseResult(String data) {
        Log.e("LoginBaseResq",data);
              if(!TextUtils.isEmpty(data)){
                  bean = JsonParseUtil.parseObject(data,Bean.class);
              }
    }
    public Bean getBean(){
        return bean;
    }
}
