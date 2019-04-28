package com.wuzz.demo.mvc.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/28
 * Time: 14:33
 * Description 描述:
 */
public class ClassScaner {

    /**
     * 扫描某个包下面的所有类
     *
     * @param basePakage
     * @return
     * @throws IOException
     */
    public static Map<String, Class<?>> scannerClass(String basePakage) throws IOException {
        Map<String, Class<?>> results = new HashMap<String, Class<?>>();
        String filePath = basePakage.replace(".", "/");
        //通过类加载器，获取完成路径
        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(filePath);
        //获取到类在磁盘的物理路径
        ///D:/apache-tomcat-9.0.6/wtpwebapps/mvcStudy/WEB-INF/classes/com/example/demo/
        String rootPath = Thread.currentThread().getContextClassLoader().getResource(filePath).getPath();
        if (rootPath != null) {
            // com/example/demo/
            rootPath = rootPath.substring(rootPath.lastIndexOf(filePath));
        }
        while (dirs.hasMoreElements()) {
            //file:/D:/apache-tomcat-9.0.6/wtpwebapps/mvcStudy/WEB-INF/classes/com/example/demo/
            URL url = dirs.nextElement();
            //判断是否文件对象
            if (url.getProtocol().equals("file")) {
                File file = new File(url.getPath().substring(1));
                //file :D:\apache-tomcat-9.0.6\wtpwebapps\mvcStudy\WEB-INF\classes\com\example\demo
                //rootPath : com/example/demo/
                scannerFile(file, rootPath, results);
            }
        }
//      System.out.println(rootPath);

        return results;
    }

    private static void scannerFile(File folder, String rootPath, Map<String, Class<?>> classes) {
        //拿到folder的所有文件对象，有文件夹也有文件
        //第一次进来：D:\apache-tomcat-9.0.6\wtpwebapps\mvcStudy\WEB-INF\classes\com\example\demo\annotation
        File[] files = folder.listFiles();
        for (int i = 0; files != null && i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {//是文件目录？  递归
                scannerFile(file, rootPath + file.getName() + "/", classes);
            } else {//非文件目录，那么就是.class文件，
                //这个是文件的绝对路径，从磁盘开始
                //例如D:\apache-tomcat-9.0.6\wtpwebapps\mvcStudy\WEB-INF\classes\com\example\demo\annotation\BaseController.class
                String path = file.getAbsolutePath();
                if (path.endsWith(".class")) {//判断是否时class文件
                    //将路径中\ 替换成/ 注意要将\转义
                    //D:/apache-tomcat-9.0.6/wtpwebapps/mvcStudy/WEB-INF/classes/com/example/demo/annotation/BaseController.class
                    path = path.replace("\\", "/");
                    //完整的类路径
                    //rootPath:com/example/demo/
                    String className = rootPath + path.substring(path.lastIndexOf("/"), path.indexOf(".class"));
                    className = className.replace("/", ".");
                    //com.example.demo.annotation.BaseController
                    try {
                        //放入map
                        classes.put(className, Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
