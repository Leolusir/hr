package com.devils.hr.authorization;

import com.devils.hr.authorization.annotations.Authorization;
import com.devils.hr.configs.AppConfig;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by AndyL on 2017/4/13.
 * 拦截器
 * 在接受到请求的时候首先验证请求是否有效
 * @see com.devils.hr.authorization.annotations.Authorization
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(! (handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //判断是否含有 Authorization 注释
        boolean needAuth = method.isAnnotationPresent(Authorization.class);
        //如果该方法开启了验证且应用也开启了验证功能
        if(AppConfig.OPEN_AUTH && needAuth){
            //进行验证
        }

        return true;
    }
}
