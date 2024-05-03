package com.ruoyi.web.controller.framework.aop;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    @Before("execution(* com.ruoyi.web.controller.*.controller.*Controller.list*(..))")
    public void beforeLog() {
        System.out.println("begin  write log ");
    }


    @Before("execution(* com.ruoyi.web.controller.*.controller.*Controller.list*(..))")
    public void afterLog() {
        System.out.println("end  write log ");
    }


}
