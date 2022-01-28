package com.example.emrestserver.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Around("com.example.emrestserver.AOP.PointCuts.inControllerLayer()")
    public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        long start = System.currentTimeMillis();

        Object value;

        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            long duration = System.currentTimeMillis() - start;

            log.info("{} {} from {} took {} ms",
                    request.getMethod(), request.getRequestURI(),
                    request.getRemoteAddr(), duration);
            // use repo and the information above to persist data
        }

        return value;
    }
}
