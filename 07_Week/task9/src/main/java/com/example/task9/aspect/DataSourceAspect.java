package com.example.task9.aspect;

import com.example.task9.router.DataSourceRouter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class DataSourceAspect {
    @Pointcut("execution(* com.*.task9.service.impl..*.*(..)))")
    public void aspect() {
    }
    @Before("aspect()")
    private void before(JoinPoint point) {
        //进入切面
        String method = point.getSignature().getName();//当前切入的方法名
        		if (method.startsWith("query") || method.startsWith("select") || method.startsWith("get")
				|| method.startsWith("find") || method.startsWith("read")) {

		DataSourceRouter.setSlave();//设置为当前使用从库
    }
        System.out.println(method);
    }
    @After("aspect()")
    public void after(JoinPoint joinPoint) {
        DataSourceRouter.setMater();
    }
}
