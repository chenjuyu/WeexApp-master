package com.app.weexapp.utils;

import android.util.Log;
import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

import com.app.weexapp.utils.HttpUitls;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyModule extends WXModule {

   /* @WXModuleAnno(runOnUIThread = true)
    public void printLog(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @WXModuleAnno(runOnUIThread = false)
    public void nativeCallBack(JSCallback callback) {
        //回调信息
        callback.invoke("I am callback message");
        1、callback.invokeAndKeepAlive(map);该方法可以调用多次
//callback.invoke(map);该方法只会调用一次。
这个就是交互的格式，上面的注解设置为true就是让其在主线程中运行，

openWx就是一个方法名，里面的两个参数：

msg就是weex给我们传的数据，

callback就是我们给weex回传的数据。

具体使用：
    } */

    HttpUitls http=new HttpUitls();

    @JSMethod(uiThread = true)
    public void printLog(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
    @JSMethod(uiThread = true)
    public void nativeCallBack(JSCallback callback) {
        //回调信息
        //callback.invoke("I am callback message");
        callback.invokeAndKeepAlive("I am callback message");
    }

    @JSMethod(uiThread = false)
    public void openWx(String msg,JSCallback callback) {
        /*openWx就是一个方法名，里面的两个参数：
msg就是weex给我们传的数据，
callback就是我们给weex回传的数据。*/
        List ls =null;
        if(!TextUtil.isEmpty(msg)) {
            Log.i("MyModule","输出传值的条件aaa：" + msg);
        }
     /*   try {
            JSONObject  json=http.getData();
          //  Log.i("MyModule","json的值到这了:"+json.getString("msg"));
               JSONArray jsonArray = json.getJSONArray("obj");
                  if (jsonArray.length() > 0) {
                    ls = new ArrayList();
                    for (int i = 0; i < jsonArray.length(); i++) {
                    //   System.out.println("查看是否有值:"+jsonArray.getJSONObject(i).getString("Name"));
                       // JSONObject a=new JSONObject(jsonArray.get(i));
                       // ls.add(jsonArray.get(i));
                        Log.i("MyModule","ls列表的值：" + i + "个:" + jsonArray.get(i));
                    }
                      Log.i("MyModule","ls列表的值："+ls.toString());
                }


        }catch (JSONException e){
            e.printStackTrace();
        } */
        ls = new ArrayList();
      for(int i=1;i<=10;i++) {
          Map<String, Object> m =new HashMap<String, Object>();
          m.put("Code",i);
          m.put("Name","陈"+String.valueOf(i));
          ls.add(m);
      }
       //JSONObject j=http.getData();;
       String str ="[{\"Code\":\"123\",\"Name\":\"陈生\"},{\"Code\":\"12\",\"Name\":\"陈生2\"}]";//http.JSONtoStr();
       // JSONObject j=http.getData();
        com.alibaba.fastjson.JSONObject js=http.JhttpRequest("http://192.168.1.25:8080/testmybatis/syndata.do?","GET","syntools&currpage=1&pagesize=20&field=EmployeeID,Code,Name&tbl=Employee&keyid=EmployeeID");;//new com.alibaba.fastjson.JSONObject();

      //  System.out.println("alibaba-OBJ:"+js.getJSONArray("obj").toString());
        //js.put("ls",str);
       /* try {
            j = new JSONObject(str);
        }catch (JSONException e) {
            e.printStackTrace();
        } */
        callback.invokeAndKeepAlive(js);



    }






}
