package org.example.backend.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log =
            LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* org.example.backend..controller..*(..))")
    public Object logControllerExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toShortString();
        log.info("Entering method: {}", methodName);
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;
            log.info("Exiting method: {} | Execution time: {} ms", methodName, duration);
            return result;
        } catch (Throwable throwable) {
            long duration = System.currentTimeMillis() - startTime;
            log.error("Exception in method: {} | Execution time: {} ms", methodName, duration, throwable);
            throw new RuntimeException(throwable);
        }
    }

}
