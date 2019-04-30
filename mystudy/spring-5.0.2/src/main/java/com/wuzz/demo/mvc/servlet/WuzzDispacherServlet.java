package com.wuzz.demo.mvc.servlet;

import com.wuzz.demo.mvc.annotation.WuzzAutowired;
import com.wuzz.demo.mvc.annotation.WuzzController;
import com.wuzz.demo.mvc.annotation.WuzzRequestMapping;
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
        loadOnStartup = 1,
        initParams = {@WebInitParam(name = "basePackage", value = "com.wuzz.demo")})
public class WuzzDispacherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //存储contro实例
    private Map<String, Object> controllers = new HashMap<String, Object>();
    //被反射调用的method
    private Map<String, Object> methods = new HashMap<String, Object>();

    //存放所扫描出来的类及其实例
    Map<String, Class<?>> ioc = new HashMap<>();

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
        Method method = (Method) methods.get(uri);
        //1.单例
//      Object controller = controllers.get(method.getDeclaringClass().getName());
        //2.多例
        BaseController controller;
        try {
            //获取实例
            controller = (BaseController) method.getDeclaringClass().newInstance();
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
            //1 扫描包得到所有的class 并且注入ioc
            doScanner(basePackage);

            //实际上这里中间可以扫描@Service @Autowired 注解实现自动的依赖注入
            //可参考DispacherServlet 的初始化流程
            //可参考DispacherServlet#initStrategies(ApplicationContext context)

            //2、初始化HandlerMapping
            initHandlerMapping();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //扫描出相关的类
    private Map<String, Class<?>> doScanner(String scanPackage) {
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
                try {
                    ioc.put(className, Class.forName(className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return ioc;
    }


    //初始化url和Method的一对一对应关系
    private void initHandlerMapping() throws IllegalAccessException, InstantiationException {
        //获取迭代器进行迭代
        Iterator<String> itro = ioc.keySet().iterator();
        while (itro.hasNext()) {
            //获取类的全类名，例如我包里有个类com.wuzz.demo.annotation.RequestMapping
            String className = itro.next();
            Class clazz = ioc.get(className);
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


    }

    //自动依赖注入 暂时无这个逻辑
    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Class<?>> entry : ioc.entrySet()) {
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


}
