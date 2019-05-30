package com.wuzz.demo.swagger2.config;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

@Component
@Order // plugin加载顺序，默认是最后加载
public class MyModelPropertyBuilderPlugin implements ModelPropertyBuilderPlugin {

	@Override
	public boolean supports(DocumentationType delimiter) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void apply(ModelPropertyContext context) {
		// TODO Auto-generated method stub
		if(context.getBuilder().build().getDescription()==null)
			context.getBuilder().isHidden(true);
	}

}
