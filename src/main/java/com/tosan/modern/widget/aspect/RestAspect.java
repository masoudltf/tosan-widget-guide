package com.tosan.modern.widget.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Mohammad Abbasi
 * @since 17/02/2021
 */
@Aspect
@Component
public class RestAspect {

    private static final Logger logger = LoggerFactory.getLogger(RestAspect.class);

    @Pointcut("execution(* com.tosan.modern.widget.service.BoomService.*(..)))" )
    private void boomServicePointCut() {
    }

    @Pointcut("execution(* com.tosan.modern.widget.service.WistoreService.*(..)))" )
    private void wistoreServicePointCut() {
    }

    @Before("boomServicePointCut() && wistoreServicePointCut()")
    public void logBeforeAllMethods(JoinPoint jp) {
        logger.info("-------------- Call " + jp.getSignature().getName() + " --------------------------");
        Object[] signatureArgs = jp.getArgs();
        for (Object signatureArg : signatureArgs) {
            logger.info("Arguments: " + signatureArg);
        }
        logger.info("----------------------------------------------------------------------------------");
    }

    @AfterThrowing(pointcut = "boomServicePointCut() && wistoreServicePointCut()", throwing = "ex")
    public void afterThrowingMethods(JoinPoint jp, Exception ex) {
        logger.error("-------------- Error in " + jp.getSignature().getName() + " --------------------------");
        logger.error(ex.getMessage());
        logger.error("---------------------------------------------------------------------------------");
    }

    @AfterReturning(value = "boomServicePointCut() && wistoreServicePointCut()", returning = "retVal")
    public void afterReturn(JoinPoint jp, Object retVal) {
        logger.error("-------------- after Return " + jp.getSignature().getName() + " --------------------------");
        logger.error(retVal.toString());
        logger.error("---------------------------------------------------------------------------------");
    }

}
