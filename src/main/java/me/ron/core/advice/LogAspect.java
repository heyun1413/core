package me.ron.core.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author sharpron
 * @date 2018/5/10
 * @since 1.0
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger("log");

    @Pointcut("execution(public * me.ron.core.service.impl.*.*(..))")
    public void serviceLog(){}

    @Before("serviceLog()")
    public void log(JoinPoint joinPoint) {
        logger.info("location = {}", joinPoint.getSignature().toLongString());
        logger.info("args = {}", joinPoint.getArgs());
    }

    @AfterReturning(returning = "ret", pointcut = "serviceLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        if (ret != null) {
            logger.info(ret.toString());
        }
    }

}
