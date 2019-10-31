package com.gerry.jnshu.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("myAdvice")
@Aspect
public class MyAdvice {

    @Before(value="execution(* *..*.*ServiceImpl.*(..))")
    public void log(){
        System.out.println("记录日志...");
    }



    @Pointcut(value = "execution(* *..*.*ServiceImpl.*(..))")
    public void log2(){
        System.out.println("统一配置切入点...");
    }
    @Before("com.gerry.jnshu.advice.MyAdvice.log2()")
    public void beforeMethod(){
        System.out.println("开始方法");
    }

    @After("com.gerry.jnshu.advice.MyAdvice.log2()")
    public void afterMethod(JoinPoint joinPoint){
        Object object = joinPoint.getSignature();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String rightnow=sdf.format(date);
        System.out.println(rightnow+"执行了【"+object+"方法结束......】");
    }

    @Around("com.gerry.jnshu.advice.MyAdvice.log2()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint){
        Object rtvalue = null;
        Object[] args = joinPoint.getArgs();
        try {
            //前置通知
            rtvalue = joinPoint.proceed(args);
            //后置通知
        } catch (Throwable e) {
            //异常通知
            e.printStackTrace();
        }
        finally{
            //最终通知
        }
        return rtvalue;
    }

    /**
     * 在方法正常执行通过之后执行的通知叫做返回通知
     * 可以返回到方法的返回值 在注解后加入returning
     * @param joinPoint
     */
    @AfterReturning(value="com.gerry.jnshu.advice.MyAdvice.log2()",returning="result")
    public void afterReturn(JoinPoint joinPoint, Object result ){
        Object object = joinPoint.getSignature();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String rightnow=sdf.format(date);
        System.out.println(rightnow+"执行了【"+object+"方法正常执行结束......】"+"【返回结果:"+result+"】");
        System.out.println("方法结束...");
    }

    /**
     * 在目标方法非正常执行完成 发生异常 抛出异常的时候会走此方法
     * 获得异常可以用throwing
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value="com.gerry.jnshu.advice.MyAdvice.log2()",throwing="ex")
    public void afterThrowing(JoinPoint joinPoint,Exception ex ){
        Object object = joinPoint.getSignature();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String rightnow=sdf.format(date);
        System.out.println(rightnow+"执行了【"+object+"方法发生异常......】"+"【异常报告:"+ex+"】");
        System.out.println("方法发生异常结束...");
    }
}

