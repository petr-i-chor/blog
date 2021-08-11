package com.jh.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jh
 * @create 2021-07-15-22:21
 */

@ControllerAdvice //控制器增强器，一个重要的功能就是来捕获自定义异常
public class ControllerExceptionHandler {

//    日志打印
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//  @ControllerAdvice + @ExceptionHandler 实现全局的 Controller 层的异常处理
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView mv = new ModelAndView();
        logger.error("request URL : {}, Exception {}",request.getRequestURL(),e);

        //不处理带有ResponseStatus注解的异常
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);


        mv.setViewName("error/error");
        return mv;
    }
}
