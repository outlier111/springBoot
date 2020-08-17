package com.hqyj.javaSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ServiceAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut("@annotation(com.hqyj.javaSpringBoot.aspect.ServiceAnnotation)")
    @Order(2)
    public void servicePointCut(){}

    @Before(value = "com.hqyj.javaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public void beforeService(JoinPoint joinPoint){
        LOGGER.debug("----------This is before service----------");
    }

    @Around("com.hqyj.javaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("----------This is around service----------");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After("com.hqyj.javaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public void afterService(){
        LOGGER.debug("-----------This is after service-----------");
    }
}
