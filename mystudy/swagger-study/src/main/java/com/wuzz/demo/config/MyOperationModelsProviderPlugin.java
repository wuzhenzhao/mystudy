package com.wuzz.demo.config;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import com.wuzz.demo.anno.ApiReturnJson;
import com.wuzz.demo.anno.ApiReturnJsonPro;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationModelsProviderPlugin;
import springfox.documentation.spi.service.contexts.RequestMappingContext;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

@Component
@Order // plugin加载顺序，默认是最后加载
public class MyOperationModelsProviderPlugin implements OperationModelsProviderPlugin {
    @Autowired
    private TypeResolver typeResolver;

    @Override
    public boolean supports(DocumentationType delimiter) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void apply(RequestMappingContext context) {
        // TODO Auto-generated method stub

        if (context.getReturnType().isInstanceOf(Map.class)) {
            List<ResolvedMethodParameter> parameters = context.getParameters();
            // 根据参数上的ApiJsonObject注解中的参数动态生成Class
            Optional<ApiReturnJson> optional = context.findAnnotation(ApiReturnJson.class);
            ApiReturnJsonPro[] properties = null;
            String name = null;
            try {
//                Method method = Swagger2.class.getMethod("createRestApi");//系统默认取该处的全局变量
//                ApiReturnJson apiReturnJson = method.getAnnotation(ApiReturnJson.class);
                name = context.getGroupName() + "_" + context.getName();
//                ApiReturnJsonPro[] properties0 = apiReturnJson.value();
                if (optional.isPresent()) {
                    properties = optional.get().value();
//                    properties = new ApiReturnJsonPro[properties1.length + properties0.length];
//                    int k = 0;
//                    for (; k < properties0.length; k++) properties[k] = properties0[k];
//                    for (int p = 0; p < properties1.length; p++) properties[k + p] = properties1[p];
                }
//                else properties = properties0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            ResolvedType rt = typeResolver.resolve(createRefModel(properties, name));
            String s = rt.toString();

            // 向documentContext的Models中添加我们新生成的Class
            context.getDocumentationContext().getAdditionalModels().add(rt);
            context.operationModelsBuilder().addReturn(rt).build();
        }
    }

    private final static String basePackage = "com.wuzz.demo.entity.custom."; // 动态生成的Class名

    /**
     * 根据propertys中的值动态生成含有Swagger注解的javaBeen
     */
    private Class<?> createRefModel(ApiReturnJsonPro[] propertys, String name) {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass(basePackage + name);
        try {
            for (ApiReturnJsonPro property : propertys) {
                ctClass.addField(createField(property, ctClass));
            }
            // 解冻CtClass对象
            ctClass.defrost();

            return ctClass.toClass();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据property的值生成含有swagger apiModelProperty注解的属性
     *
     * @throws Exception
     */
    private CtField createField(ApiReturnJsonPro property, CtClass ctClass)
            throws Exception {
        CtField ctField = new CtField(getFieldType(property.dataType()), property.key(), ctClass);
        ctField.setModifiers(Modifier.PUBLIC);

        ConstPool constPool = ctClass.getClassFile().getConstPool();

        AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        javassist.bytecode.annotation.Annotation ann = new javassist.bytecode.annotation.Annotation(
                "io.swagger.annotations.ApiModelProperty", constPool);

        ann.addMemberValue("value", new StringMemberValue(property.description(), constPool));
        MemberValue value = null;
        if (property.dataType().isAssignableFrom(String.class))
            value = new StringMemberValue(property.example(), constPool);
        else if (property.dataType().isAssignableFrom(Integer.class))
            value = new IntegerMemberValue(Integer.parseInt(property.example()), constPool);
        else if (property.dataType().isAssignableFrom(Long.class))
            value = new LongMemberValue(Long.parseLong(property.example()), constPool);
        else if (property.dataType().isAssignableFrom(Boolean.class))
            value = new BooleanMemberValue(Boolean.parseBoolean(property.example()), constPool);
        else if (property.dataType().isAssignableFrom(Double.class))
            value = new DoubleMemberValue(Integer.parseInt(property.example()), constPool);
        else if (property.dataType().isAssignableFrom(Enum.class))
            value = new EnumMemberValue(constPool);
        else if (property.dataType().isAssignableFrom(Float.class))
            value = new FloatMemberValue(Float.parseFloat(property.example()), constPool);
        else if (property.dataType().isAssignableFrom(Array.class))
            value = new ArrayMemberValue(constPool);
        else if (property.dataType().isAssignableFrom(Class.class))
            value = new ClassMemberValue(property.dataType().toString(), constPool);
        //else throw new Exception(property.dataType()+"不识别");
        if (value != null)
            ann.addMemberValue("example", value);
        attr.addAnnotation(ann);
        ctField.getFieldInfo().addAttribute(attr);
        return ctField;
    }

    private CtClass getFieldType(Class<?> type) throws NotFoundException {
        CtClass fileType = ClassPool.getDefault().get(type.getName());
        return fileType;
    }
}
