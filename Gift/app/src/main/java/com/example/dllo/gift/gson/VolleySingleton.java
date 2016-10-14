package com.example.dllo.gift.gson;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.gift.home.MyApp;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * Created by 刘城羊 on 16/7/10.
 */
public class VolleySingleton {
    private static Context context;
    private RequestQueue requestQueue;
    private static VolleySingleton volleySingleton = new VolleySingleton();
    public static VolleySingleton getVolleySingleton(){
        return volleySingleton;
    }
    private VolleySingleton(){
//        context = MyApp.getContext();
        context = MyApp.getmContext();
        requestQueue = getRequestQueue();
    }
    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            synchronized (VolleySingleton.class){
                if (requestQueue == null){
                    requestQueue = Volley.newRequestQueue(context);
                }
            }
        }
        return requestQueue;
    }
    public <T> void _addRequest(Request<T> request){
        requestQueue.add(request);
    }
    public <T> void _addRequest(Request<T> request,Object tag){
        request.setTag(tag);
        _addRequest(request);

    }
    public void _addRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener){
        StringRequest stringRequest = new StringRequest(url,listener,errorListener);
        _addRequest(stringRequest);
    }
    public <T> void _addRequest(String url, Class<T> mClass, Response.Listener<T> listener, Response.ErrorListener errorListener){
        GsonRequest<T> gsonRequest = new GsonRequest<T>(Request.Method.GET,url,mClass,listener,errorListener);
        _addRequest(gsonRequest);

    }
    public static <T> void addRequest(Request<T> request){
        getVolleySingleton()._addRequest(request);
    }
    public static <T> void addRequest(Request<T> request,Object tag){
        getVolleySingleton()._addRequest(request,tag);
    }
    public static <T> void addRequest(String url, Response.Listener listener, Response.ErrorListener errorListener){
        getVolleySingleton()._addRequest(url,listener,errorListener);
    }
    public static <T> void addRequest(String url,Class<T> mClass,Response.Listener listener,Response.ErrorListener errorListener){
        getVolleySingleton()._addRequest(url,mClass,listener,errorListener);
    }
    public void removeRequest(Object tag){
        requestQueue.cancelAll(tag);
    }

}
