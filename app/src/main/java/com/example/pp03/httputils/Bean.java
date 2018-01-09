package com.example.pp03.httputils;

import com.example.pp03.httputils.HttpUtils.ReflectBean;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pp03 on 2018-01-09.
 */

public class Bean extends ReflectBean{

    @SerializedName("data")
    public List<DataBean> list;

}
