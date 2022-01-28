package com.example.emrestserver.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointCuts {
    @Pointcut("within(com.example.emrestserver.controller..*)")
    public void inControllerLayer() {

    }
}
