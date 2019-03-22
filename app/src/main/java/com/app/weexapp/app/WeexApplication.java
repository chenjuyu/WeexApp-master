package com.app.weexapp.app;

import android.app.Application;
import android.util.Log;

import com.app.weexapp.utils.ImageAdapter;
import com.app.weexapp.utils.MyModule;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/**
 * Application
 * Created by Acoe on 2018/4/4.
 */

public class WeexApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InitConfig.Builder builder = new InitConfig.Builder();
        builder.setImgAdapter(new ImageAdapter());
        InitConfig config = builder.build();
        WXSDKEngine.initialize(this, config);
        try {
            //通信方法 myModule是weex调用原生的方法名
            WXSDKEngine.registerModule("myModule", MyModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
        Log.i("Application", "WXSDKEngine.isInitialized: " + WXSDKEngine.isInitialized());

    }
}