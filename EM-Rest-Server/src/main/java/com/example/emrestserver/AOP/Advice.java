package com.example.emrestserver.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Advice {

    Logger log = LoggerFactory.getLogger(this.getClass());


    @Around("com.example.emrestserver.AOP.PointerCuts.inDataAccessLayer()")
    public Object executionTimeAdvice(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.warn("before processed");

        Object result = pjp.proceed();

        long elapsedTime = System.currentTimeMillis() - startTime;
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        log.warn(className+"."+methodName+" execution time: "+elapsedTime+" ms "+"result: "+result);
        return result;
    }
}
