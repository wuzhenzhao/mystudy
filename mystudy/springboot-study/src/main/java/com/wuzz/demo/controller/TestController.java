package com.wuzz.demo.controller;

import com.wuzz.demo.core.Result;
import com.wuzz.demo.entity.EntityDemo;
import com.wuzz.demo.exception.BusinessException;
import com.wuzz.demo.exception.CommonErrorEnum;
import com.wuzz.demo.exception.ErrorObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 14:29
 * Description 描述:
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public Result test(EntityDemo entity) {

//        entity.validate();
        if (entity.getId() == null)
            throw new BusinessException(CommonErrorEnum.PARAMER_ERROR);
//            throw new BusinessException(new ErrorObject("001", "error", "数据异常"));

        return new Result(true, "000", "成功");
    }
}
