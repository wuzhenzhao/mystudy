package com.wuzz.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/21
 * Time: 19:01
 * Description 描述:
 */
public class Test1 {
    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){
        StringBuffer buffer=null;
        try{
            URL url=new URL(requestUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);
            conn.connect();
            //往服务器端写内容 也就是发起http请求需要带的参数
            if(null!=outputStr){
                OutputStream os=conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            //读取服务器端返回的内容
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null){
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
//        String s = httpRequest("http://192.168.1.101:80/", "GET", null);
//        System.out.println(s);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
         Runnable runnable = new Runnable() {

                @Override
                public void run() {
//                        String s = httpRequest("http://192.168.1.101:80/", "GET", null);
                        System.out.println("1111111");


                }
            };
            executorService.execute(runnable);
    }
}
