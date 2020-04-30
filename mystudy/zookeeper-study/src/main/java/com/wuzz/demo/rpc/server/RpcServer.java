package com.wuzz.demo.rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/4/30 13:50
 * @since 1.0
 **/
public class RpcServer {
    //创建一个线程池
    private static final ExecutorService executorService= Executors.newCachedThreadPool();

    private IRegisterCenter registerCenter; //注册中心
    private String serviceAddress; //服务发布地址

    // 存放服务名称和服务对象之间的关系
    Map<String,Object> handlerMap=new HashMap<>();

    public RpcServer(IRegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * 绑定服务名称和服务对象
     * @param services
     */
    public void bind(Object... services){
        for(Object service:services){// 这里为了获取对应服务的类名，我们这里定义了一个注解来实现 代码请看下面
            RpcAnnotation annotation=service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName=annotation.value().getName();
            handlerMap.put(serviceName,service);//绑定服务接口名称对应的服务
        }
    }

    public void publisher(){
        ServerSocket serverSocket=null;
        try{
            String[] addrs=serviceAddress.split(":");//这个时候服务的ip port 都是从这个注册地址上获取
            serverSocket=new ServerSocket(Integer.parseInt(addrs[1]));  //启动一个服务监听
            // handlerMap 可能存放多个发布服务，我这里演示的是一个
            for(String interfaceName:handlerMap.keySet()){
                registerCenter.register(interfaceName,serviceAddress);
                System.out.println("注册服务成功："+interfaceName+"->"+serviceAddress);
            }

            while(true){ //循环监听
                Socket socket=serverSocket.accept(); //监听服务
                //通过线程池去处理请求
                executorService.execute(new ProcessorHandler(socket,handlerMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
