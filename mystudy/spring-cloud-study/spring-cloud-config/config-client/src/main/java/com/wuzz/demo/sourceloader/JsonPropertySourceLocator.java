package com.wuzz.demo.sourceloader;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
@Component
public class JsonPropertySourceLocator implements PropertySourceLocator{

    private final static String DEFAULT_LOCATION="classpath:wuzz.json";

    private final ResourceLoader resourceLoader=new DefaultResourceLoader(getClass().getClassLoader());

    @Override
    public PropertySource<?> locate(Environment environment) {
        WuzzDefineJsonPropertySource jsonPropertySource=
                new WuzzDefineJsonPropertySource("jsonPropertyConfig",mapPropertySource());
        return jsonPropertySource;
    }

    private Map<String,Object> mapPropertySource(){
        //访问远程配置？http接口。
        Resource resource=this.resourceLoader.getResource(DEFAULT_LOCATION);
        if(resource==null){
            return null;
        }
        Map<String,Object> result=new HashMap<>();
        JsonParser jsonParser= JsonParserFactory.getJsonParser();
        Map<String,Object> fileMap=jsonParser.parseMap(readFile(resource));
        processorMap("",result,fileMap);
        return result;
    }

    private void processorMap(String prefix,Map<String,Object> result,Map<String,Object> fileMap){
        if(prefix.length()>0){
            prefix+=".";
        }
        for (Map.Entry<String,Object> entrySet:fileMap.entrySet()){
            if(entrySet.getValue() instanceof Map){
                processorMap(prefix+entrySet.getKey(),result,(Map<String,Object>)entrySet.getValue());
            }else{
                result.put(prefix+entrySet.getKey(),entrySet.getValue());
            }
        }
    }

    /**
     * JSON格式
     * {
     *     {
     *         {
     *
     *         }
     *     }
     * }
     * @param resource
     * @return
     */
    private String readFile(Resource resource){
        FileInputStream fileInputStream=null;
        try{
            fileInputStream=new FileInputStream(resource.getFile());
            byte[] readByte=new byte[(int)resource.getFile().length()]; //TODO 错误演示
            fileInputStream.read(readByte);
            return new String(readByte,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
