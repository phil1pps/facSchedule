package com.example.facSchedule;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("within(com.example.facSchedule.service.SpecialityService)")
    public void stringProcessingMethods() {
    };

    @After("stringProcessingMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature()
                .getName();

        logger.log(Level.INFO, "Method name: " + methodName);
    }

    @AfterReturning(pointcut = "execution(public String com.example.facSchedule.service.SpecialityService.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        logger.log(Level.INFO, "returned value: " + result.toString());
    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        logger.log(Level.INFO, joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}