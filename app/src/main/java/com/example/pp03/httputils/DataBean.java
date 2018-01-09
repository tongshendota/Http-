package com.example.pp03.httputils;

import com.example.pp03.httputils.HttpUtils.ReflectBean;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pp03 on 2018-01-09.
 */

public class DataBean extends ReflectBean{
    /**
     * id : 1
     * url : http://192.168.50.109/imgae/a.png
     * key_word : a
     * flag : 1
     */
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("key_word")
    private String keyWord;
    @SerializedName("flag")
    private int flag;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getKeyWord() {
        return keyWord;
    }
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
}
