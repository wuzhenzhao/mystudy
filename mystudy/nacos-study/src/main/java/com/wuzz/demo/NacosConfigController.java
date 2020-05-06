package com.wuzz.demo;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/6 15:44
 * @since 1.0
 **/
@NacosPropertySource(type = ConfigType.YAML, dataId = "example", groupId = "DEFAULT_GROUP", autoRefreshed = true)
@RestController
public class NacosConfigController {

    /**
     * 当前的info这个属性，回去nacos-server找到对应的info这个属性
     * 高可用性
     * hello Nacos表示本地属性（降级属性）
     * 　　　* autoRefreshed ：是否自动刷新配置变更
     */
    @NacosValue(value = "${name:hello Nacos}", autoRefreshed = true)
    private String name;

    @GetMapping("/get")
    public String get() {
        return name;
    }
}
