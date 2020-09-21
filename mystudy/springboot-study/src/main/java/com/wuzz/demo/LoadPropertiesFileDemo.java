package com.wuzz.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/28 12:24
 * @since 1.0
 **/
public class LoadPropertiesFileDemo {

    public static void main(String[] args) throws IOException {
        //加载文件
//        //服务器查组件目录资源 /opt/opsmgr/web/components/cem.1/bin/cemweb/config/
//        String path = this.getClass().getResource("/").getPath();
//
//        String rootPath = path.split("bin")[0] + "resource";
//        FileSystemResource fileSystemResource = new FileSystemResource(rootPath);
//        boolean exists = fileSystemResource.exists();
//        EncodedResource en = null;
//        if (!exists) {
//
//            en = new EncodedResource(new ClassPathResource("eventType.yml"), "UTF-8");
//        } else {
//            en = new EncodedResource(new FileSystemResource("D:/eventType2.yml"), "UTF-8");
//        }
////        en = new EncodedResource(new ClassPathResource("eventType.yml"), "UTF-8");
//        Properties properties = PropertiesLoaderUtils.loadProperties(en);


        //1实时加载配置文件，修改后立即生效，不必重启。
        EncodedResource en =new EncodedResource(new ClassPathResource("warnType.properties"),"UTF-8");
        Properties properties = PropertiesLoaderUtils.loadProperties(en);
        //2 只能加载类classes下面的资源文件，且只能读取.properties文件
        ResourceBundle bundle = ResourceBundle.getBundle("warnType.properties");
        //3.只能加载类classes下面的资源文件。
        InputStream resourceAsStream = LoadPropertiesFileDemo.class.getClassLoader().getResourceAsStream("message.properties");
        //4.使用的是当前类的相对路径。
        LoadPropertiesFileDemo.class.getResourceAsStream("warnType.properties");
        //5 @PropertySource
        //6 @ConfigurationProperties(prefix = "")
    }
}
