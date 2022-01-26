package com.example.emrestserver.AOP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("com.example.emrestserver.AOP.PointCuts.thisEmployeeService()")
    public void thisImpl2BeforeAdvice(JoinPoint joinPoint){
        log.warn("this Impl2 before advice: " + joinPoint.getSignature());
    }

    @Around("com.example.emrestserver.AOP.PointCuts.inDataAccessLayer()")
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

    @After("com.example.emrestserver.AOP.PointCuts.inBeanWithSuffixController()")
    public void inBeanWithSuffixControllerAfterAdvice(JoinPoint joinPoint){
        log.warn("After Advice: "+ joinPoint.getSignature());
    }

    @AfterReturning(value = "com.example.emrestserver.AOP.PointCuts.methodWithinClassWithAnnotationRestController()", returning = "res")
    public void afterReturningAdvice(Object res){
        log.warn("After Returning: " + res.toString());
    }

    @AfterThrowing("com.example.emrestserver.AOP.PointCuts.methodWithinClassWithAnnotationRestController()")
    public void afterThrowing(JoinPoint joinPoint){
        log.warn("After Throwing: " + joinPoint.getSignature());
    }
}


