package com.lin.test.client.interceptor;

import com.lin.test.common.util.RequestUtil;
import com.lin.test.dao.model.UpmsLog;
import com.lin.test.rpc.api.UpmsApiService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Aspect
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private long startTime = 0L;

    private long endTime = 0L;

    @Autowired
    private UpmsApiService upmsApiService;

    @Before("execution(* *..controller..*.*(..))")
    public void doBefore(JoinPoint point) {
        logger.info("doBefore");
        startTime = System.currentTimeMillis();
    }

    @After("execution(* *..controller..*.*(..))")
    public void doAfter(JoinPoint point) {
        logger.debug("doAfter");
    }

    @Around("execution(* *..controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        UpmsLog upmsLog = new UpmsLog();
        //获取操作名称，响应结果
        Object result = pjp.proceed();
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            upmsLog.setDescription(apiOperation.value());
        }
        if (method.isAnnotationPresent(RequiresPermissions.class)) {
            RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
            String[] permissions = requiresPermissions.value();
            if (permissions != null && permissions.length > 0) {
                upmsLog.setPermissions(permissions[0]);
            }
        }
        endTime = System.currentTimeMillis();


        logger.debug("doAround >>>> result {} ，消耗时间 {} ", result, endTime - startTime);
        upmsLog.setBasePath(RequestUtil.getBasePath(httpServletRequest));
        upmsLog.setStartTime(startTime);
        upmsLog.setSpendTime((int) (endTime - startTime));
        upmsLog.setUri(httpServletRequest.getRequestURI());
        upmsLog.setUrl(ObjectUtils.toString(httpServletRequest.getRequestURL()));
        upmsLog.setUserAgent(ObjectUtils.toString(httpServletRequest.getHeader("User-Agent")));
        upmsLog.setUsername(ObjectUtils.toString(httpServletRequest.getUserPrincipal()));
        upmsApiService.insertUpmsLogSelective(upmsLog);
        return result;
    }
}
