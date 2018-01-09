package com.example.pp03.httputils.HttpUtils;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONException;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.lzy.okgo.request.PostRequest;

import java.io.File;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 童岳洲
 * 网络请求类
 * on 2018-01-09.
 */

public class HttpUtils {

    private final String Tag = "HttpUtils";

    private static HttpUtils instance;

    public static HttpUtils getInstance(){
        if(instance==null){
            synchronized (HttpUtils.class){
                if(instance==null){
                    instance = new HttpUtils();
                }
            }

        }
        return instance;
    }
    public HttpUtils(){

    }
    public void post(Object tag, final BaseReq request, final HttpRequestCallBack callBack){
        Log.e(Tag,"request url:"+request.getRequestUrl());
        PostRequest okGoRequest = OkGo.getInstance().post(request.getRequestUrl());
        addParams(okGoRequest,request);
        okGoRequest.execute(new AbsCallback<BaseResp>() {

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                if (callBack!=null){
                    callBack.onBefore();
                }
            }

            @Override
            public void onAfter(BaseResp baseResp, Exception e) {
                super.onAfter(baseResp, e);
                if (callBack!=null){
                    callBack.onAfter();
                }
            }

            @Override
            public BaseResp convertSuccess(Response response) throws Exception {
                String result = response.body().string();
//                Log.e(Tag,result);
                return JsonParseUtil.parseObject(result, BaseResp.class);
            }

            @Override
            public void onSuccess(BaseResp bean, Call call, Response response) {
                try {
                    if (bean!=null){
                        Log.e(Tag,"data: "+bean.code+"  "+bean.msg+"  "+bean.data);
                        if (bean.code==200) {
                            request.parseResponseResult(bean.data);
                            if (callBack!=null){
                                callBack.onSuccess();
                            }
                        }else{
                            onFailCall(callBack,bean.code,bean.msg);
                        }
                    }else{
                        onFailCall(callBack,0,"数据错误");
                    }
                }catch (Exception exception){
                    Log.e(Tag,exception.getMessage());
                    if (exception instanceof JSONException){
                        onFailCall(callBack, ErrorState.JSONPROXY_ERROR, "数据解析失败");
                    }else {
                        onFailCall(callBack, 0, "处理异常");
                    }
                }
            }

            @Override
            public void onError(Call call, Response response, Exception excepction) {
//                Log.e(Tag,excepction.getMessage());
                if (excepction instanceof java.net.UnknownHostException || excepction instanceof ConnectException){
                    onFailCall(callBack,ErrorState.NET_ERROR,"请求失败，请检查您的网络连接!");
                }else if (excepction instanceof ConnectException){
                    onFailCall(callBack,ErrorState.NET_ERROR,"请求失败，请检查您的网络连接!");
                }else if (excepction instanceof SocketTimeoutException) {

                }else{
                    onFailCall(callBack,0,excepction.getMessage());
                }
            }
        });
    }

    private void onFailCall(HttpRequestCallBack callBack,int errcode,String msg){

        if (callBack!=null){
            if (callBack.needShowErrorTip()){

                Log.e(Tag,msg+"("+errcode+")");
//                ToastUtil.showCustomToast(msg+"("+errcode+")");
            }
            callBack.onFail(errcode,msg);
        }else{
//            ToastUtil.showCustomToast(msg+"("+errcode+")");
        }
    }

    private void addParams(PostRequest postRequest, BaseReq request) {
        HashMap<String,String> params = request.getParamsMap();
        for (String key:params.keySet()){
            postRequest.params(key,params.get(key));
//            Log.e(Tag,key+"--"+params.get(key));
        }
    }

//    /**下载图片**/
//    public void loadImg(Object tag, String imgUrl, BitmapCallback callback){
//        OkGo.get(InterfaceContacts.HOST+"/index.php?c="+InterfaceContacts.INTERFACE_C_MERCHANT+"&m="+InterfaceContacts.INTERFACE_READIMGFILE+"&imagePath="+imgUrl+"&lkey="+GmApplication.getLkey())//
//                .tag(this)//
//                .execute(callback);
//    }

    public void uploadFile(Object tag, BaseReq uploadReq, HashMap<String,File> fileMap, StringCallback callback){
        PostRequest request =  OkGo.post(uploadReq.getRequestUrl()).tag(tag);
        addParams(request,uploadReq);
        for (String key:fileMap.keySet()){
            request.params(key,fileMap.get(key));
        }
        request.execute(callback);
              /*  .params("file1", new File("filepath1"))   // 可以添加文件上传
                .params("file2", new File("filepath2")) 	// 支持多文件同时添加上传
                .addFileParams("key", List<File> files)	// 这里支持一个key传多个文件
                execute(callbacknew StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //上传成功
                    }


                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
                    }
                });*/
    }
}
