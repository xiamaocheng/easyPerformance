package com.ruoyi.web.controller.framework.aop;

import com.ruoyi.web.controller.framework.config.TimeoutConfig;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class PerformanceAspect {


//    1ms 3 条记录
//    27   81 条记录

    @Autowired
    private TimeoutConfig timeoutConfig;


    @Around("execution(* com.ruoyi.web.controller.*.controller.*Controller.list*(..))")
    public Object measureInsertPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {

//            针对系统级，类的级别，方法级别，进行性能显示
            //01.测算的响应时间：方法的执行时间应该尽量减少，避免长时间的等待和阻塞。
            log.info(">>>>>>>>performacen Test beign  .....");
            long startTime = System.currentTimeMillis();
            result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            if (executionTime > timeoutConfig.getRead()) {
                log.error("查询接口已超时，请优化接口");
            }
            log.info("Method executed in " + executionTime + "ms");
            log.info("<<<<<<<<performance Test End   .....");


            //02.测试内存占用
            log.info(">>>>>>>>performacen Test beign  .....");

            long memStart = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
              result = joinPoint.proceed();
            long memEnd = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long executionTimeMem = memEnd - memStart;

            log.info("Method executed in " + executionTimeMem + "ms");
            log.info("<<<<<<<<performance Test End   .....");


            //03.测试小号CPU的多少




        } catch (Throwable e) {
            log.error("方法执行异常", e);
            throw new RuntimeException(e);
        }
        return result;
    }



}
