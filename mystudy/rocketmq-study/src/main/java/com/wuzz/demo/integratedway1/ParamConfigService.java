package com.wuzz.demo.integratedway1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * User: Wuzhenzhao
 * Date: 2019/12/10
 * Time: 13:55
 * Description:配置参数绑定
 * ClassPath:com.wuzz.demo.integratedway1.ParamConfigService
 */
@Service
public class ParamConfigService {

    @Value("${plat.plat-group}")
    public String platGroup ;
    @Value("${plat.plat-topic}")
    public String platTopic ;
    @Value("${plat.plat-tag}")
    public String accountTag ;

    public String getPlatGroup() {
        return platGroup;
    }

    public void setPlatGroup(String platGroup) {
        this.platGroup = platGroup;
    }

    public String getPlatTopic() {
        return platTopic;
    }

    public void setPlatTopic(String platTopic) {
        this.platTopic = platTopic;
    }

    public String getAccountTag() {
        return accountTag;
    }

    public void setAccountTag(String accountTag) {
        this.accountTag = accountTag;
    }
}
