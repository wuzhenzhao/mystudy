package com.wuzz.demo.controller;

import com.wuzz.demo.anno.ApiReturnJson;
import com.wuzz.demo.anno.ApiReturnJsonPro;
import com.wuzz.demo.model.RestMessage;
import com.wuzz.demo.model.RestRequest;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/10 10:59
 * @since 1.0
 **/
@RestController
//Api用在请求的类上，说明该类的作用,tags=“说明该类的作用”
@Api(tags = "用户信息维护")
public class DemoController {

    @GetMapping("/test1")
    //ApiOperation用在请求的方法上，说明方法的作用value=“说明方法的作用”、notes=“方法的备注说明”
    @ApiOperation(value = "test_ApiOperation", notes = "参数name，一定要传")
    public void test1(@ApiParam(value = "用户名称", required = true) @RequestParam String name) {
        System.out.println(name);
    }

    //paramType ：查询参数类型，这里有几种形式：
    //path 以地址的形式提交数据
    //query 直接跟参数完成自动映射赋值
    //body 以流的形式提交 仅支持POST
    //header 参数在request headers 里边提交
    //form 以form表单的形式提交 仅支持POST

    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, paramType = "query", example = "1888888888"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", example = "**********"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, paramType = "query", example = "16")
    })
    @GetMapping("/test2")
    @ApiOperation(value = "test_ApiImplicitParams")
    public RestMessage test2(String mobile, String password, Integer age) {
        return new RestMessage();
    }

    //@ApiResponses：用于请求的方法上，表示一组响应
    @ApiResponses({//@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
//            code：数字，例如400
//            message：信
//            息，例如"请求参数没填好"
//            response：抛出异常的类
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    @GetMapping("/test3")
    @ApiOperation(value = "test_ApiResponses")
    public RestMessage test3(String name) {
        return new RestMessage();
    }

    //Swagger要传送对象作为参数，只需添加@ModelAttribute或@RequestBody
    @PostMapping("/test4")
    @ApiOperation(value = "test_@ApiModel")
    public RestMessage test4(@RequestBody RestRequest request) {
        return new RestMessage();
    }


    @ApiReturnJson(key = "modelMap_api", value = {
            @ApiReturnJsonPro(key = "modelMap", description = "map返回测试",dataType = RestMessage.class),
            @ApiReturnJsonPro(key = "name", description = "我的名字"),
            @ApiReturnJsonPro(key = "age", description = "我的年龄")
    })
    @ApiOperation(value = "获取用户详细信息", notes = "根据name来获取用户详细信息")
    @RequestMapping(value = "/test_returnMap", method = {RequestMethod.GET})
    public Map resultMap() {
        Map map = new HashMap();
        map.put("modelMap", Arrays.asList(new RestMessage()));
        map.put("name", "wuzzMapDemo");
        map.put("age", "wuzzMapDemo");
        return map;
    }
}
