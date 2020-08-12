package com.example.demo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by Jocelyn on 2018/8/4.
 *
 * @author hs
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 统计请求的处理时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
    /**
     * 定义日志切入点
     */
//    @Pointcut("execution(public * com.bonc.controller.*.*(..)) || execution(* com.bonc.mapper.*.*(..)) ")
    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
    public void logCut() {
    }

    /**
     * 在切入点开始处切入内容
     *
     * @param joinPoint
     */
    @Before("logCut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("LogAspect logBefore begin-------------------------------------------------------");

        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录请求内容
        log.info("uri : " + request.getRequestURI());
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            System.out.println(paraName + ": " + request.getParameter(paraName));
        }
    }

    /**
     * 在切入点return内容之后切入内容
     *
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "logCut()")
    public void logAfterReturning(Object object) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info(request.getRequestURL().toString() + "接口用时：" + (System.currentTimeMillis() - startTime.get()) + "ms");
        log.info("LogAspect logAfterReturning end------------------------------------------------");
    }


}
