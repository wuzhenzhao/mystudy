//package com.wuzz.demo.config;
//
//import com.alibaba.dubbo.common.Constants;
//import org.apache.dubbo.common.extension.Activate;
//import org.apache.dubbo.rpc.*;
//
///**
// * @description: 类功能描述
// * @author: wuzhenzhao
// * @time 2021/5/27 16:23
// * @since 1.0
// **/
//@Activate(
//        group = {Constants.PROVIDER, Constants.CONSUMER},
//        order = -2000
//)
//public class XXXDubboGlobalFilter implements Filter {
//
//    @Override
//    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//
//        System.out.println("start DubboGlobalFilter.....");
//        return invoker.invoke(invocation);
//    }
//}
