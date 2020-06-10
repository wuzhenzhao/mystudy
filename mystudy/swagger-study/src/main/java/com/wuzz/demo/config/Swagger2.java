package com.wuzz.demo.config;

import com.wuzz.demo.anno.ApiReturnJson;
import com.wuzz.demo.anno.ApiReturnJsonPro;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/6/10 10:50
 * @since 1.0
 **/
@Configuration
public class Swagger2 {
    //http://localhost:8777/swagger-ui.html
    @ApiReturnJson(key ="",value = {
            @ApiReturnJsonPro(key = "code", example = "0000", description = "处理码"),
            @ApiReturnJsonPro(key = "msg", example = "成功", description = "处理失败（code!=0000）提示信息")
    })
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wuzz.demo"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("标题APIS")
                .description("描述。。。。。")
                .termsOfServiceUrl("团队服务地址")
                .version("1.0")
                .build();
    }


}
