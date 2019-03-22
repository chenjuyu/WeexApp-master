package com.app.weexapp.utils;


import android.util.Log;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpUitls {

   public HttpUitls(){}

   private  static   JSONObject  json;

   public static JSONObject getData() {
    new Thread(
            new Runnable(){
        @Override
        public void run () {
            try {
              //  URL url = new URL("http://192.168.1.25:8080/testmybatis/syndata.do?syntools&currpage=1&pagesize=20&field=EmployeeID,Code,Name&tbl=Employee&keyid=EmployeeID");

             json=JhttpRequest("http://192.168.1.25:8080/testmybatis/syndata.do?","GET","syntools&currpage=1&pagesize=20&field=EmployeeID,Code,Name&tbl=Employee&keyid=EmployeeID");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }).start();
       System.out.println("httUitls中json的值:"+json);
    return  json;
}

 public static  String JSONtoStr(){
      getData();
       String str=json.toString();
       System.out.println("Str的值："+str);
       return  str;
 }


    public static com.alibaba.fastjson.JSONObject JhttpRequest(String requestUrl, String requestMethod, String outputStr)
    {
        com.alibaba.fastjson.JSONObject jsonObject = null;

        try
        {
	    /*  TrustManager[] tm = { new MyX509TrustManager() };
	      SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	      sslContext.init(null, tm, new SecureRandom());

	      SSLSocketFactory ssf = sslContext.getSocketFactory(); */

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection(); //https
            // conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);

            conn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)) {
                conn.connect();
            }

            if (outputStr != null) {
                OutputStream outputStream = conn.getOutputStream();

                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
           // System.out.println("\r返回内容:" + buffer.toString());

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();

            jsonObject = JSON.parseObject(buffer.toString());
            System.out.println("httUitls中JSONObject的值:"+jsonObject);
            return jsonObject;//JSONObject.fromObject(buffer.toString());
        }
        catch (ConnectException ce) {
            System.out.println("连接超时：{}"+ ce.toString());
            return JhttpRequest(requestUrl, requestMethod, outputStr);
        } catch (Exception e) {
            System.out.println("连接超时：{}"+ e.toString());
        }return jsonObject;
    }
/*
    public static void   main(String[] args){
        //System.out.println(getData().toString());

        //String str="{'msg':'返回成功'}";
        //getData();
       System.out.println("测试测试");
       String str ="{'id':123,'name':'我们从人'}";
   //    net.sf.json.JSONObject json=new net.sf.json.JSONObject.fromObject(str);
   //     System.out.println(json.getString("name"));

      try {
          JSONObject json = new JSONObject(str);
      }catch (JSONException e){
          e.printStackTrace();
      }



    }  */
}
