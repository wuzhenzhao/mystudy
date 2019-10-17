package com.wuzz.demo.web;

import com.wuzz.demo.core.Result;
import com.wuzz.demo.core.exception.BusinessException;
import com.wuzz.demo.core.exception.CommonErrorEnum;
import com.wuzz.demo.entity.EntityDemo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/10/15
 * Time: 15:37
 * Description 描述:
 */
@Controller
public class IndexController {


    @RequestMapping("/thymeleaf")
    public String index() {
        return "index";
    }
}
