package com.example.pp03.httputils.HttpUtils;

import java.util.HashMap;

/**
 * Created by 童岳洲 on 2018-01-09.
 */

public abstract class BaseReq {
    protected HashMap<String,String> paramsMap = new HashMap<>();
    /**获取c 类型**/
    public abstract String getInterfaceCtype();

    /**获取m 类型**/
    public abstract String getInterfaceMtype();

    protected abstract void parseResponseResult(String data);

    public String getRequestUrl(){
        return "http://192.168.50.109"+"/index.php?c="+getInterfaceCtype()+"&m="+getInterfaceMtype();
    }

    public HashMap<String, String> getParamsMap() {
        return paramsMap;
    }
}
