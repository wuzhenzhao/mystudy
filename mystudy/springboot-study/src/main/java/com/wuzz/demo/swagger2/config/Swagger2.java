package com.wuzz.demo.swagger2.config;

import com.wuzz.demo.swagger2.annotation.ApiReturnJson;
import com.wuzz.demo.swagger2.annotation.ApiReturnJsonPro;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/5/7
 * Time: 14:39
 * Description 描述:
 */
@Configuration
@EnableSwagger2
@Profile({"dev","local"})
public class Swagger2 {
    public static final HashSet<String> consumes = new HashSet<String>() {
        /**  @Fields serialVersionUID : TODO */
        private static final long serialVersionUID = 1L;
        {
            add("application/x-www-form-urlencoded");
        }
    };

    @ApiReturnJson(key ="",value = {
            @ApiReturnJsonPro(key = "code", example = "0000", description = "处理码"),
            @ApiReturnJsonPro(key = "msg", example = "成功", description = "处理失败（code!=0000）提示信息")
    })
    @Bean
    public Docket restApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(true).pathProvider(new CustRelativePathProvider()).select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(setHeaderToken())
                .apiInfo(apiInfo()).consumes(Swagger2.consumes);
        return docket;
    }
    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("token").description("X-Auth-Token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("springboot整合swagger2")
                .description("处理状态码code值说明：</br>"
                        + "0000 -- 操作成功</br>"
                        + "1000 -- 参数为空或非法</br>"
                        + "1001 -- 添加失败</br>"
                        + "1002 -- 更新失败</br>"
                        + "1003 -- 删除失败</br>"
                        + "1004 -- 操作失败</br>"
                        + "1005 -- 已存在</br>"
                        + "1006 -- 密码错误</br>"
                        + "1007 -- 用户被锁定或者删除</br>"
                        + "1008 -- 限制重复提交</br>"
                        + "1009 -- 数据不存在</br>"
                        + "1010 -- 两次输入不一样</br>"
                        + "1011 -- 验证码错误</br>"
                        + "1012 -- 无效</br>"
                        + "9110 -- 非法操作</br>"
                        + "9111 -- token签名与本地计算签名不匹配</br>"
                        + "9112 -- token过期</br>"
                        + "9990 -- 系统异常</br>"
                        + "9999 -- 未知异常</br>")
                .termsOfServiceUrl("")
                .title("手表系统接口文档")
                .version("1.0")
                .build();
    }

    public class CustRelativePathProvider extends AbstractPathProvider {
        public static final String ROOT = "/";

        @Override
        public String getOperationPath(String operationPath) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
            String uri = uriComponentsBuilder.path(operationPath).build().toString();
            return uri + ".do";
        }

        @Override
        protected String getDocumentationPath() {
            return ROOT;
        }

        @Override
        protected String applicationPath() {
            // TODO Auto-generated method stub
            return ROOT;
        }
    }
}
