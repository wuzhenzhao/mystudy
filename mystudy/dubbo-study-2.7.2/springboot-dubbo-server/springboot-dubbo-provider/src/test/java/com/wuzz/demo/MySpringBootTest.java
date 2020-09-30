package com.wuzz.demo;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Protocol;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/9/29 14:24
 * @since 1.0
 **/
@SpringBootTest
public class MySpringBootTest {

    @Test
    public void test() {
        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
//        LoadBalance balance = ExtensionLoader.getExtensionLoader(LoadBalance.class)
//                .getExtension("wuzzLoadBalance");
//        System.out.println(balance);
//        ExtensionLoader<Filter>
//                loader = ExtensionLoader.getExtensionLoader(Filter.class);
//        URL url = new URL("", "", 0);
//        url = url.addParameter("cache", "cache");
//        List<Filter> filters = loader.getActivateExtension(url, "cache");
//        System.out.println(filters.size());
    }
}
