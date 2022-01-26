package com.example.emrestserver.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCuts {
    @Pointcut("within(com.example.emrestserver.dao..*)")
    public void inDataAccessLayer() {}
    @Pointcut("bean(*Service)")
    public void inBeanWithSuffixService() {}

    @Pointcut("bean(*Controller)")
    public void inBeanWithSuffixController() {}

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void methodWithinClassWithAnnotationRestController() {}


    @Pointcut("this(com.example.emrestserver.service.EmployeeService)")
    public void thisEmployeeService(){}
}
