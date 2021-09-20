package com.example.jvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        loadXlass("Hello","hello");
	}

    /**
     * 加载Xlass
     */
    private static void loadXlass(String className,String methodName){
        XlassLoad xlassLoad = new XlassLoad();
        try {
            //加载类
            Class<?> loadClass = xlassLoad.loadClass(className);
            //获取并查看有那些方法
            Method[] declaredMethods = loadClass.getDeclaredMethods();
            for (Method method:declaredMethods) {
                System.out.println(method.getName());
            }
            //实例对象
            Object o = loadClass.getDeclaredConstructor().newInstance();
            //调用方法
            Method method = loadClass.getDeclaredMethod(methodName);
            method.invoke(o);

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
