package com.devils.hr.authorization.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by AndyL on 2017/4/13.
 * 验证注解
 * 该注解修饰的请求路径会在进入 controller 对其进行验证
 * @see com.devils.hr.authorization.AuthorizationInterceptor
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

}
