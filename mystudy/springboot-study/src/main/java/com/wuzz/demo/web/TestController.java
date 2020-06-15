package com.wuzz.demo.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.wuzz.demo.core.Result;
import com.wuzz.demo.core.exception.BusinessException;
import com.wuzz.demo.core.exception.CommonErrorEnum;
import com.wuzz.demo.entity.EntityDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 14:29
 * Description 描述:
 */
@RestController
@RequestMapping("/wuzz")
public class TestController {
    private final static Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/post.json", method = {RequestMethod.POST})
    public Result insert(EntityDemo entity) {
        log.info("进入insert方法。。。。。。。");
        entity.validate();
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

    @RequestMapping(value = "/pus", method = {RequestMethod.GET})
    public Result get1(EntityDemo entity) {
        log.info("进入pus方法。。。。。");
//        entity.validate();
        return new Result(true, "000", "成功1111111: ");
    }

    @RequestMapping(value = "/test1", method = {RequestMethod.GET})
    public Result test1(@Valid EntityDemo entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BusinessException("错误提示码 : " + bindingResult.getFieldError().getDefaultMessage());
        }
        return new Result(true, bindingResult);
    }

    @RequestMapping(value = "/test2", method = {RequestMethod.GET})
    @JsonView(Result.BaseJsonView.class)
    public Result test2() {

        EntityDemo entityDemo = new EntityDemo();
        entityDemo.setId("1");
        entityDemo.setName("Tome");
        entityDemo.setAge(20.0);
        return new Result(true, new EntityDemo());
    }

    @RequestMapping(value = "/test3", method = {RequestMethod.GET})
    @JsonView(Result.SimpleJsonView.class)
    public Result test3() {
        EntityDemo entityDemo = new EntityDemo();
        entityDemo.setId("2");
        entityDemo.setName("james");
        entityDemo.setAge(25.0);
        return new Result(true, new EntityDemo());
    }

    @RequestMapping(value = "/test4", method = {RequestMethod.GET})
    public Result test4() {
        EntityDemo entityDemo = new EntityDemo();
        entityDemo.setId("3");
        entityDemo.setName("james");
        entityDemo.setAge(55.0);
        return new Result(true, new EntityDemo());
    }
}
