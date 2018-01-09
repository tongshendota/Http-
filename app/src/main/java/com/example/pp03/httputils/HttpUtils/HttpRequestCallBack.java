package com.example.pp03.httputils.HttpUtils;

import android.app.Activity;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by 童岳洲 on 2018/01/09.
 * 网络回调设置
 */

public abstract class HttpRequestCallBack {
    private SweetAlertDialog pDialog;
    private Activity mActivity;
    public HttpRequestCallBack(Activity activity){
       mActivity = activity;
    }
    public HttpRequestCallBack(){
        mActivity = null;
    }
    public abstract void onSuccess();
    public void onFail(int errcode,String msg){

    }
    public void onBefore(){
        if (mActivity!=null&&!mActivity.isFinishing()) {
             pDialog = new SweetAlertDialog(mActivity, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading");
            pDialog.setCancelable(false);
            pDialog.show();
        }
    };
    public void onAfter(){
        if (pDialog!=null&&pDialog.isShowing()) {
            pDialog.dismiss();
        }
    };

    public boolean needShowErrorTip(){
        return true;
    }
}
