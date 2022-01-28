package com.example.emrestserver.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

@Component
@Aspect
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


    @Before("com.example.emrestserver.AOP.PointerCuts.inBeanWithSuffixController() && args(..,request)")
    public void inBeanWithSuffixControllerAfterAdvice(JoinPoint joinPoint, HttpServletRequest request){
        log.warn("*********After Advice: "+ joinPoint.getSignature());
        log.warn("Entering in Method :  " + joinPoint.getSignature().getName());
        log.warn("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        log.warn("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
        log.warn("Target class : " + joinPoint.getTarget().getClass().getName());

        if (null != request) {
            log.warn("Start Header Section of request ");
            log.warn("Method Type : " + request.getMethod());
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = (String) headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                log.warn("Header Name: " + headerName + " Header Value : " + headerValue);
            }
            log.warn("Request Path info :" + request.getServletPath());
            log.warn("End Header Section of request ");
        }



    }

    @AfterReturning(pointcut = "com.example.emrestserver.AOP.PointerCuts.inBeanWithSuffixController()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {

        log.warn("Method Return value : " + result.toString());
    }

}
