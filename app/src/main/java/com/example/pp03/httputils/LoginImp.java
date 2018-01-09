package com.example.pp03.httputils;

import android.app.Activity;
import android.util.Log;

import com.example.pp03.httputils.HttpUtils.HttpRequestCallBack;
import com.example.pp03.httputils.HttpUtils.HttpUtils;

/**
 * Created by pp03 on 2018-01-09.
 */

public class LoginImp implements LoginPer{
       LoginView loginView;
       Activity activity;
    public LoginImp(LoginView iLoginView, Activity activity){
          loginView = iLoginView;
          this.activity = activity;
    }

    @Override
    public void dologin(final String name,String password){
        final LoginBaseResq loginReq = new LoginBaseResq(name,password);
        HttpUtils.getInstance().post(activity, loginReq, new HttpRequestCallBack() {
            @Override
            public void onSuccess() {
                if(loginReq.getBean()!=null){
                   if(loginReq.getBean().list!=null){
                       loginView.loginresutle(200,loginReq.getBean().list);
                   }
                }
            }
        });
    }
}
