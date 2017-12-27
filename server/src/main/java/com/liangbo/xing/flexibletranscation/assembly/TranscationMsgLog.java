package com.liangbo.xing.flexibletranscation.assembly;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午11:36 xingliangbo Exp $
 */

@Aspect
@Configuration
public class TranscationMsgLog {

    public static org.slf4j.Logger logger = LoggerFactory.getLogger(TranscationMsgLog.class);

    @Autowired
    private LogAssembler logAssembler;


    @Pointcut("execution(* com.liangbo.xing.flexibletranscation.service.impl..*(..))")
    public void excuteService() {
    }

    @Around("excuteService()")
    public Object doLog(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String name = thisJoinPoint.getSignature().getName();
        Object[] args = thisJoinPoint.getArgs();
        Object result = null;
        try {
            logger.debug("start {} params {} ", name, args);
            result = thisJoinPoint.proceed();
        } catch (Exception e) {
            ChainInfo chainInfo = new ChainInfo();
            chainInfo.setMethod(name);
            chainInfo.setParams(args);
            chainInfo.setInfo(e.getMessage());
            try {
                //记录关键日志到db 比如 发布消息失败
                if(name.startsWith("publish")){
                    logAssembler.logInDb(chainInfo);
                }
            } catch (Exception e1) {
                logger.error("log in db error", e1);
            }
            logger.error("start {} params {} error msg: {}", name, args, e);
            throw e;
        }
        return result;
    }

}
