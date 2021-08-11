package com.jh.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jh
 * @create 2021-07-17-18:37
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.jh.blog.controller.*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //请求URl
        String URL = request.getRequestURL().toString();
        //访问者IP
        String ip = request.getRemoteAddr();
        //方法名称
        String classMethod = joinPoint.getSignature().getDeclaringType() +"."+ joinPoint.getSignature().getName();
        //方法参数
        Object[] args = joinPoint.getArgs();
        RequestLog log = new RequestLog(URL,ip,classMethod,args);
        logger.info("Request {}",log);

    }
    @After("log()")
    public void after(){
        logger.info("--------after执行了-------");

    }
    @AfterReturning(returning = "result",pointcut = "log()")
    public void afterReturning(Object result){
        //返回值
        logger.info("result={}",result);

    }

}
