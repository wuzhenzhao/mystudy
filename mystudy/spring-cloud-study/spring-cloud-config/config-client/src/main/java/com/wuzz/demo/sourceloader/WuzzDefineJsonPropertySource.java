package com.wuzz.demo.sourceloader;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
public class WuzzDefineJsonPropertySource extends EnumerablePropertySource<Map<String, Object>> {

    public WuzzDefineJsonPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    public String[] getPropertyNames() {
        return StringUtils.toStringArray(this.source.keySet());
    }

    @Override
    public Object getProperty(String name) {
        return this.source.get(name);
    }
}
