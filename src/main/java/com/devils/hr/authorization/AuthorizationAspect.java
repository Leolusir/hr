package com.devils.hr.authorization;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * Created by AndyL on 2017/4/13.
 * 处理被
 * @see com.devils.hr.authorization.annotations.Authorization
 * 注释的方法
 */
@Aspect
@Configuration
public class AuthorizationAspect {

    @Pointcut("@annotation(com.devils.hr.authorization.annotations.Authorization)")
    public void annotationPointcut(){}

    @Before("annotationPointcut()")
    public void before(){
        //do something after authorization pass, before method
    }

}
