package com.example.auth.AOP;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
@Aspect
public class PointCuts {
    @Pointcut("within(com.example.auth.dao..*)")
    public void inDaoLayer() {
    }
    @Pointcut("within(com.example.auth.controller..*)")
    public void inControllerLayer() {
    }

}
