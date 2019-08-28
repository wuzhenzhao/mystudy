package com.wuzz.demo.swagger2.web;

import com.wuzz.demo.core.Result;
import com.wuzz.demo.core.exception.BusinessException;
import com.wuzz.demo.core.exception.CommonErrorEnum;
import com.wuzz.demo.entity.EntityDemo;
import com.wuzz.demo.swagger2.annotation.ApiReturnJson;
import com.wuzz.demo.swagger2.annotation.ApiReturnJsonPro;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/11
 * Time: 14:29
 * Description 描述:
 */
@RestController()
public class Swaggar2TestController {


    @ApiOperation(value = "新建用户", notes = "新建用户详细信息")
    @RequestMapping(value = "/swagger2/post.json", method = {RequestMethod.POST})
    public Result insert(EntityDemo entity) {

//        entity.validate();
        if (entity.getId() == null)
            throw new BusinessException(CommonErrorEnum.PARAMER_ERROR);
//            throw new BusinessException(new ErrorObject("001", "error", "数据异常"));

        return new Result(true, "000", "成功: " + entity);
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据name来获取用户详细信息")
    @ApiImplicitParam(name = "name", value = "参数名字", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value = "/swagger2/get.json", method = {RequestMethod.GET})
    public Result get(@RequestHeader(value = "name") String name) {

        if (name == null)
            throw new BusinessException(CommonErrorEnum.PARAMER_ERROR);
        return new Result(true, "000", "成功: ");
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据name来获取用户详细信息")
    @ApiImplicitParam(name = "name", value = "参数名字", required = true, dataType = "String", paramType = "header")
    @ApiResponses({
            @ApiResponse(code = 1000001, message = "返回姓名")
    })
    @RequestMapping(value = "/swagger2/returnString.json", method = {RequestMethod.GET})
    public String resultDemo(@RequestHeader(value = "name") String name) {

        if (name == null)
            throw new BusinessException(CommonErrorEnum.PARAMER_ERROR);

        return name;
    }
    @ApiReturnJson(key = "modelMap_api", value = {
            @ApiReturnJsonPro(key = "modelMap", description = "map返回测试"),
            @ApiReturnJsonPro(key = "name", description = "我的名字"),
            @ApiReturnJsonPro(key = "age", description = "我的年龄")
    })
    @ApiOperation(value = "获取用户详细信息", notes = "根据name来获取用户详细信息")
    @RequestMapping(value = "/swagger2/returnMap.json", method = {RequestMethod.GET})
    public Map resultMap() {
        Map map = new HashMap();
        map.put("modelMap", "wuzzMapDemo");
        map.put("name", "wuzzMapDemo");
        map.put("age", "wuzzMapDemo");


        return map;
    }

}
