package com.wuzz.demo.web;

import com.wuzz.demo.SpringBootStudyApp;
import com.wuzz.demo.core.Result;
import com.wuzz.demo.core.exception.BusinessException;
import com.wuzz.demo.core.exception.CommonErrorEnum;
import com.wuzz.demo.entity.EntityDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 14:29
 * Description 描述:
 */
@RestController
@RequestMapping("wuzz")
public class TestController {
    private final static Logger log = LoggerFactory.getLogger(TestController.class);
    @RequestMapping(value = "/post.json", method = {RequestMethod.POST})
    public Result insert(EntityDemo entity) {
        log.info("进入insert方法。。。。。。。");
        entity.validate();
        if (entity.getId() == null) {
            log.info("ID 为空。。。。");
            throw new BusinessException(CommonErrorEnum.PARAMER_ERROR);
        }
//            throw new BusinessException(new ErrorObject("001", "error", "数据异常"));

        return new Result(true, "000", "成功: " + entity);
    }

    @RequestMapping(value = "/get.json", method = {RequestMethod.GET})
    public Result get(String name) {
        log.info("进入get方法。。。。。");
        if (name == null) {
            log.info("name为空。。。。。。。");
            throw new BusinessException(CommonErrorEnum.PARAMER_ERROR);
        }
        return new Result(true, "000", "成功: ");
    }

    @RequestMapping(value = "pus", method = {RequestMethod.GET})
    public Result get1(EntityDemo entity) {
        log.info("进入pus方法。。。。。");
        entity.validate();
        return new Result(true, "000", "成功1111111: ");
    }
}
