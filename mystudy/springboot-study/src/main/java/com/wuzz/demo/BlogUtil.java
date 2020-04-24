package com.wuzz.demo;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlogUtil {
    private static Desktop desktop;//定义私有静态成员变量
    public static ArrayList<String> strList = new ArrayList<String>();//定义String类型的泛型集合

    //以下是我的博文的网址
    public static String str = "https://blog.csdn.net/weixin_41358004/article/details/103725821";
    public static String str1 = "https://blog.csdn.net/weixin_41358004/article/details/103712843";
    public static String str2 = "https://blog.csdn.net/weixin_41358004/article/details/103814181";
    public static String str3 = "https://blog.csdn.net/weixin_41358004/article/details/103839940";
    public static String str8 = "https://blog.csdn.net/weixin_41358004/article/details/";
    public static String str4 ="https://blog.csdn.net/weixin_41358004/article/details/103987655";
    public static String str5 ="https://blog.csdn.net/weixin_41358004/article/details/103918275";

    /*
     * 定义了一个通过默认浏览器打开相应网址的方法
     * 这个方法还用到了有关Desktop类的一些内容，大家可以自行百度或者查看相应API
     */
    public static void browse(String uri) {

        if (Desktop.isDesktopSupported()) {//判断是否支持DeskTop
            desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(uri));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /*
     * 调用这个方法将事先写好的网址写入泛型集合strList
     */
    static {
        strList.add(str);
        strList.add(str1);
        strList.add(str2);
        strList.add(str3);
        strList.add(str4);
        strList.add(str5);
        for (int i = 103676359; i < 103676490; i++) {
            strList.add(str8 + String.valueOf(i));
        }
        for (int i = 104833089; i < 104833092; i++) {
            strList.add(str8 + String.valueOf(i));
        }
        strList.add(str5);
        strList.add(str4);
        strList.add(str3);
        strList.add(str2);
        strList.add(str);
        strList.add(str1);
    }

    public static void doAdd() {
        int i = 0;
        int a = 0;
        boolean q=true;
        while (q) {    //一直循环
            try {
                if (i < strList.size()-1)    //判断是否小于博文数量，不然可能会出现越界错误
                {
                    browse((String) strList.get(i));
                    Thread.sleep(2000);	//这里的单位为毫秒 我这里设置每个 网址之间间隔30s 这里可以根据情况改
                }
                if (a > 10) {
                    // 启用cmd运行chrome的方式来退出
                    Runtime.getRuntime().exec("taskkill /F /IM chrome.exe"); //我默认浏览器是 chrome
                    //如果你的事firefox 将chrome改为firefox即可
                    a = 0;
                    Thread.sleep(3000);	//这里的单位为毫秒 我这里设置每个访问所有博文后 休眠1小时 这里可以根据情况改
                }
                if (i > strList.size()-1) {
                    i = -1; //将i重置为-1 因为后面会进行i++ 加1后就变成了0
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            a++;
            i++;
        }

    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        executorService.execute(() -> {
            doAdd();
        });
    }
}
