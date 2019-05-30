package com.wuzz.demo.swagger2.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Component
@Order  //plugin加载顺序，默认是最后加载
public class MyOperationBuilderPlugin implements OperationBuilderPlugin  {
	@Override
	public boolean supports(DocumentationType delimiter) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void apply(OperationContext operationContext) {
		// TODO Auto-generated method stub
		if(operationContext.getReturnType().isInstanceOf(Map.class)) {
			//根据参数上的ApiJsonObject注解中的参数动态生成Class
			//Optional<ApiReturnJson> optional = operationContext.findAnnotation(ApiReturnJson.class); 
			try {
				//Method method = Swagger2.class.getMethod("restApi");//系统默认取该处的全局变量
				//ApiReturnJson apiReturnJson = method.getAnnotation(ApiReturnJson.class);
				String name = operationContext.getGroupName()+"_"+operationContext.getName();
	            Set<ResponseMessage> set = new HashSet<ResponseMessage>();
	            ModelRef mr = new ModelRef(name);
                set.add(new ResponseMessage(200,"返回json用例说明",mr,null,null));
                operationContext.operationBuilder().responseMessages(set);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
