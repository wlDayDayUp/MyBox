package com.wl1217.mybox;

import android.app.Application;
import java.util.concurrent.TimeUnit;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.OkHttpClient;
import rxhttp.wrapper.param.RxHttp;

public class MyApp extends Application {

    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

//        Funlib.Companion.init("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJgA1FF0cceexRyBRET/s0Vv21SETpKJ12Q1AnX5bGu0ZpzMKazK2DHgmnw9/uRNGkDhi5qFd0EDrd2EhesdoM0YiBjcgF8QVMBrrwDfg/ymgVJHTbvfXc9z6VrxBNZ0tR7p66vjJvmeGIpdIj1xTDJPIUa5iZtIitf0aj7R/QcFAgMBAAECgYEAl+MkRayU0UK+dghZfpT/vx/Ry7dViCpC17f8mIQ/KbSfYIjGEAq1U8m6tPGCFQFSfYii5in9izKw9owpAVKezSgrX84M67Olf1e3/F3kP2mvcpt+9J1zvmwAIaGEnH749dStMhYKUXrSVYztYEbW/KWEVOlr66NtO2BlX4nPn/UCQQDJFPi2kmvP+jRmGvObuMmP5VKeDUa6a5x8ed9+lW4k2RcR/o4MRziK200K7Yc1zxOh3nGV56nXpYYoFGQ8Lo+vAkEAwYRtfE7CB11wlhxjRmEUeNDvgDcKOxMTYqVzJTJhnf6HpMBLf2CaN6llpqnzd/5v8U/hNTB4qW5FNUwxzVDtiwJAOujp9JLxAv1KXJ+IvxZj9sQ4cBVzoynjrpQF5g/hNOpk1+C7vN0gs42MBKeR9TG1jrackE5Oc98KbrOKqhb6AQJAQAB3YXR+0YbC1LLA1qcG6UpY27PYa19MuwRzR6sZA/MJk6CRl6gweRZKa0usHVSGW24K0tecIJU6yHzRv9DzLwJAY0vP9EPY/eFKZ2aY7y4i5nqthnr2hHLG+oqJuFZryv1dc091oYkHL+1a3Tb9bkjOGNEVFCAdayL3O9f6HNNy3Q==");
        initRxHttp();   /* 初始化网络请求 */
    }

    /**
     * 初始化网络请求
     */
    private void initRxHttp() {

        //设置RxJava 全局异常处理
        RxJavaPlugins.setErrorHandler(throwable -> {
            /*
              RxJava2的一个重要的设计理念是：不吃掉任何一个异常,即抛出的异常无人处理，便会导致程序崩溃
              这就会导致一个问题，当RxJava2“downStream”取消订阅后，“upStream”仍有可能抛出异常，
              这时由于已经取消订阅，“downStream”无法处理异常，此时的异常无人处理，便会导致程序崩溃
             */
        });
        RxHttp.setDebug(BuildConfig.DEBUG);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);
        RxHttp.init(builder.build());
    }
}