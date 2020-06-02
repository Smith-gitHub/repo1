package com.achp.controller;


import com.achp.domain.SysLog;
import com.achp.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {


    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    private Date visitTime;
    private Class clazz;
    private Method method;



    @Before("execution(* com.achp.controller.*.*(..)) && !execution(* com.achp.controller.SysLogController.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args == null || args.length==0){
            method = clazz.getMethod(methodName);
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classArgs);
        }

    }

    @After("execution(* com.achp.controller.*.*(..)) && !execution(* com.achp.controller.SysLogController.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime();
        String url = "";
        if (clazz != null && method != null && clazz != LogAop.class){
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if ( classAnnotation != null){
                String[] classValue = classAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
            }
        }

        String ip = request.getRemoteAddr();

        SecurityContext context = SecurityContextHolder.getContext();

        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);
        sysLog.setMethod(clazz.getName() + method.getName());

        sysLogService.save(sysLog);

    }
}
