package com.wuzz.demo.mvc.servlet;

import com.wuzz.demo.mvc.annotation.WuzzAutowired;
import com.wuzz.demo.mvc.annotation.WuzzController;
import com.wuzz.demo.mvc.annotation.WuzzRequestMapping;
import com.wuzz.demo.mvc.annotation.WuzzService;
import com.wuzz.demo.mvc.controller.BaseController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/4/28
 * Time: 11:29
 * Description 描述:
 */
@WebServlet(urlPatterns = {"*.do"},
        loadOnStartup = 1,
        initParams = {@WebInitParam(name = "basePackage", value = "com.wuzz.demo")})
public class WuzzDispacherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //保存url和Method的对应关系 可以抽象成一个对象Handler
    private Map<String, Method> handlerMapping = new HashMap<String, Method>();

    //保存扫描的所有的类名
    private List<String> classNames = new ArrayList<String>();

    //存放所扫描出来的类及其实例
    private Map<String, Object> ioc = new HashMap<String, Object>();

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
        //访问地址http://localhost:8081/wuzz/index.do
        //这里拿到uri : /wuzz/index.do
        String uri = req.getRequestURI();
        //从方法map里获取到映射到的方法实例 : public void com.example.demo.annotation.TestController.index()
        //处理成相对路径
        if (!this.handlerMapping.containsKey(uri)) {
            resp.getWriter().write("404 Not Found!!!");
            return;
        }


        Method method = this.handlerMapping.get(uri);
        //通过反射拿到method所在class，拿到class之后还是拿到class的名称
        //再调用toLowerFirstCase获得beanName
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        //1.单例
//      Object controller = controllers.get(method.getDeclaringClass().getName());
        //2.多例
        BaseController controller;
        try {
            //获取实例
            controller = (BaseController) ioc.get(beanName);
            //初始化该controller的请求与响应
            //也就是我们的请求中参数怎么通过requset.getParam方法拿到的原因
            System.out.println(req.getRequestURI());
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
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //获取基础扫描包： 这里设定为com.wuzz.demo
        String basePackage = config.getInitParameter("basePackage");

        //1 扫描包得到所有的class 并且注入ioc
        doScanner(basePackage);
        //2、初始化扫描到的类，并且将它们放入到ICO容器之中
        doInstance();

        //3.实际上这里中间可以扫描@Service @Autowired 注解实现自动的依赖注入
        //可参考DispacherServlet 的初始化流程
        //可参考DispacherServlet#initStrategies(ApplicationContext context)
        doAutowired();
        //4、初始化HandlerMapping
        initHandlerMapping();

    }

    //扫描出相关的类
    private void doScanner(String scanPackage) {

        //scanPackage = com.gupaoedu.demo ，存储的是包路径
        //转换为文件路径，实际上就是把.替换为/就OK了
        //classpath
        URL url = this.getClass().getClassLoader().getResource("" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                classNames.add(className);
            }
        }
    }

    private void doInstance() {
        //初始化，为DI做准备
        if (classNames.isEmpty()) {
            return;
        }

        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);

                //什么样的类才需要初始化呢？
                //加了注解的类，才初始化，怎么判断？
                //为了简化代码逻辑，主要体会设计思想，只举例 @Controller和@Service,
                // @Componment...就一一举例了
                if (clazz.isAnnotationPresent(WuzzController.class)) {
                    Object instance = clazz.newInstance();
                    //Spring默认类名首字母小写
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, instance);
                } else if (clazz.isAnnotationPresent(WuzzService.class)) {
                    //1、自定义的beanName
                    WuzzService service = clazz.getAnnotation(WuzzService.class);
                    String beanName = service.value();
                    //2、默认类名首字母小写
                    if ("".equals(beanName.trim())) {
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }

                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                    //3、根据类型自动赋值,投机取巧的方式
                    for (Class<?> i : clazz.getInterfaces()) {
                        if (ioc.containsKey(i.getName())) {//接口若有多个实现
                            throw new Exception("The “" + i.getName() + "” is exists!!");
                        }
                        //把接口的类型直接当成key了
                        ioc.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //如果类名本身是小写字母，确实会出问题
    //但是我要说明的是：这个方法是我自己用，private的
    //传值也是自己传，类也都遵循了驼峰命名法
    //默认传入的值，存在首字母小写的情况，也不可能出现非字母的情况

    //为了简化程序逻辑，就不做其他判断了，大家了解就OK
    //其实用写注释的时间都能够把逻辑写完了
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        //之所以加，是因为大小写字母的ASCII码相差32，
        // 而且大写字母的ASCII码要小于小写字母的ASCII码
        //在Java中，对char做算学运算，实际上就是对ASCII码做算学运算
        chars[0] += 32;
        return String.valueOf(chars);
    }

    //自动依赖注入
    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            //Declared 所有的，特定的 字段，包括private/protected/default
            //正常来说，普通的OOP编程只能拿到public的属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(WuzzAutowired.class)) {
                    continue;
                }
                WuzzAutowired autowired = field.getAnnotation(WuzzAutowired.class);

                //如果用户没有自定义beanName，默认就根据类型注入
                //这个地方省去了对类名首字母小写的情况的判断，这个作为课后作业
                //小伙伴们自己去完善
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    //获得接口的类型，作为key待会拿这个key到ioc容器中去取值
                    beanName = field.getType().getName();
                }

                //如果是public以外的修饰符，只要加了@Autowired注解，都要强制赋值
                //反射中叫做暴力访问， 强吻
                field.setAccessible(true);

                try {
                    //用反射机制，动态给字段赋值
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //初始化url和Method的一对一对应关系
    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();

            if (!clazz.isAnnotationPresent(WuzzController.class)) {
                continue;
            }


            //保存写在类上面的@GPRequestMapping("/demo")
            String baseUrl = "";
            if (clazz.isAnnotationPresent(WuzzRequestMapping.class)) {
                WuzzRequestMapping requestMapping = clazz.getAnnotation(WuzzRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //默认获取所有的public方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(WuzzRequestMapping.class)) {
                    continue;
                }

                WuzzRequestMapping requestMapping = method.getAnnotation(WuzzRequestMapping.class);
                //优化
                // //demo///query
                String url = ("/" + baseUrl + "/" + requestMapping.value())
                        .replaceAll("/+", "/");
                handlerMapping.put(url, method);
                System.out.println("Mapped :" + url + "," + method);
            }
        }
    }
}
