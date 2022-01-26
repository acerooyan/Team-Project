package com.example.auth.AOP;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("com.example.auth.AOP.PointCuts.inDaoLayer()")
    public Object executionTimeAdvice(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        log.warn("before processed");

        Object result = pjp.proceed();

        long elapsedTime = System.currentTimeMillis() - startTime;
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        log.warn(className + "." + methodName + " execution time: " + elapsedTime + " ms " + "result: " + result);
        return result;
    }

    @Before("com.example.auth.AOP.PointCuts.inControllerLayer()")
    public void beforeMethod(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = servletRequestAttributes.getRequest();


        String requestURI = ((HttpServletRequest) request).getRequestURI();
        String method = request.getMethod();
//    String remoteAddr = request.getRemoteAddr();

//    String jsonString = JSON.toJSONString(joinPoint.getArgs());
        String jsonString = joinPoint.getArgs().toString();


        log.info("--- request information --------");

//    log.info("remoteAddr: {} ",remoteAddr);
        log.info("requestURI : {}", requestURI);
        log.info("Controller : {}", joinPoint.getTarget().getClass());
        log.info("method type: {}", method);
        log.info("req paras: {}", jsonString);
        log.info("--- request information -------- ---");
    }

    @AfterReturning(pointcut = "com.example.auth.AOP.PointCuts.inControllerLayer()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String returnValue = this.getValue(result);
        log.debug("Method Return value : " + returnValue);
    }

    private String getValue(Object result) {
        String returnValue = null;
//        if (null != result) {
//            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
//                returnValue = ReflectionToStringBuilder.toString(result);
//            } else {
//                returnValue = result.toString();
//            }
//        }


        returnValue = result.toString();

        return returnValue;
    }
}


