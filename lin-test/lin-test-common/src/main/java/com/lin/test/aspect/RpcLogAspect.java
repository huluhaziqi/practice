package com.lin.test.aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcLogAspect {

    private static Logger logger = LoggerFactory.getLogger(RpcLogAspect.class);

    private long startTime = 0L;

    private long endTime = 0L;


    @Before("execution(* *..rpc..*.*(..))")
    public void doBefore(JoinPoint joinPoint) {
        logger.debug("doBefore");
        startTime = System.currentTimeMillis();
    }

    @After("execution(* *..rpc..*.*(..))")
    public void doAfter(Joinpoint joinpoint) {
        logger.debug("doAfter");
        endTime = System.currentTimeMillis();
    }

    @Around("execution(* *..rpc..*.*(..)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();

        boolean consumeSide = RpcContext.getContext().isConsumerSide();

        String ip = RpcContext.getContext().getRemoteHost();

        String rpcUrl = RpcContext.getContext().getUrl().getParameter("application");
        logger.info("consumeSide = {}, ip ={},rpcUrl = {}", consumeSide, ip, rpcUrl);
        return result;
    }


}
