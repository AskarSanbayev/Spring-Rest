package com.epam.buscompany.service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Aspect
@Service
@Slf4j
public class LoggingAspect {

@Pointcut("execution(* com.epam.buscompany.service.impl..*(..))")
    public void serviceMonitor() {}

    @Pointcut("execution(* com.epam.buscompany.controller..*(..))")
    public void controllerMonitor() {}

    @Before("serviceMonitor()|| controllerMonitor()")
    public void monitorLogger(JoinPoint jp) {
        log.debug("Running method:" + jp.getSignature().getName());
    }

    @AfterReturning("serviceMonitor() || controllerMonitor()")
    public void monitorLoggerFinish(JoinPoint jp) {
        log.debug("Finished method:" + jp.getSignature().getName());
    }
}
