package com.wuzz.demo.mvc.servlet;

import com.wuzz.demo.mvc.annotation.WuzzController;
import com.wuzz.demo.mvc.annotation.WuzzRequestMapping;
import com.wuzz.demo.mvc.controller.BaseController;
import com.wuzz.demo.mvc.util.ClassScaner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/28
 * Time: 11:29
 * Description 描述:
 */
@WebServlet(urlPatterns = {"*.do"},
        loadOnStartup=1,
        initParams = {@WebInitParam(name = "basePackage", value = "com.wuzz.demo.mvc.controller")})
public class WuzzDispacherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //存储contro实例
    private Map<String, Object> controllers = new HashMap<String, Object>();
    //被反射调用的method
    private Map<String, Object> methods = new HashMap<String, Object>();

    public WuzzDispacherServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //访问地址http://localhost:8081/wuzz/index
        //这里拿到uri : /mvcStudy/wuzz/index.do
        String uri = req.getRequestURI();
        //拿到根路径 /mvcStudy
        //从方法map里获取到映射到的方法实例 : public void com.example.demo.annotation.TestController.index()
        Method method =(Method) methods.get(uri);
        //1.单例
//      Object controller = controllers.get(method.getDeclaringClass().getName());
        //2.多例
        BaseController controller;
        try {
            //获取实例
            controller = (BaseController)method.getDeclaringClass().newInstance();
            //初始化该controller的请求与响应
            //也就是我们的请求中参数怎么通过requset.getParam方法拿到的原因
            controller.init(req, resp);
            //然后调用该方法
            method.invoke(controller);

        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //获取基础扫描包： 这里设定为com.wuzz.demo
        String basePackage = config.getInitParameter("basePackage");
        try {
            //扫描包得到所有的class
            Map<String, Class<?>> cons = ClassScaner.scannerClass(basePackage);
            //获取迭代器进行迭代
            Iterator<String> itro = cons.keySet().iterator();
            while (itro.hasNext()) {
                //获取类的全类名，例如我包里有个类com.wuzz.demo.annotation.RequestMapping
                String className = itro.next();
                Class clazz = cons.get(className);
                String path = "";
                //判断该类是否有我指定的Controller.class注解
                if (clazz.isAnnotationPresent(WuzzController.class)) {
//                  System.out.println(clazz.getName() + "被标记为controller");
                    //判断clazz是否存在注解@RequestMapping
                    if (clazz.isAnnotationPresent(WuzzRequestMapping.class)) {
                        //取出注解的值 放入path
                        WuzzRequestMapping reqAnno = (WuzzRequestMapping) clazz.getAnnotation(WuzzRequestMapping.class);
                        //拿到controller上注解@RequestMapping指定的值
                        path = reqAnno.value().toString();
                    }
                    //将对应的类的全类名及实例存入map
                    controllers.put(className, clazz.newInstance());
                    Method[] ms = clazz.getMethods();//拿到控制类所有公开方法遍历
                    for (Method method : ms) {
                        //如果不存在该注解  就进入下一轮
                        if (!method.isAnnotationPresent(WuzzRequestMapping.class)) {
                            continue;
                        }
                        //将拿到的controller上与方法上的@RequestMapping注解的值拼起来  与该方法的实例存入map
                        //例如：/wuzz/index=public void com.example.demo.annotation.TestController.index()
                        methods.put(path + method.getAnnotation(WuzzRequestMapping.class).value(), method);
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
