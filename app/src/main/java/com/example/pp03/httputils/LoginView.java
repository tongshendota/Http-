package com.example.pp03.httputils;

import com.example.pp03.httputils.HttpUtils.BaseReq;

import java.util.List;

/**
 * Created by pp03 on 2018-01-09.
 */

public interface LoginView {
   void loginresutle(int code, List<DataBean> data);
}
