package com.gerry.jnshu.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class MyFactoryBean implements FactoryBean<Object>, InitializingBean, DisposableBean {
    private Logger logger  = LogManager.getLogger(MyFactoryBean.class);

    private Object proxyObj;
    private String interfaceName;
    private Object target;
    @Override
    public void destroy() throws Exception {
        logger.debug("destroy------>");
    }

    @Override
    public Object getObject() throws Exception {

        return proxyObj;
    }

    @Override
    public Class<?> getObjectType() {
        return proxyObj==null?Object.class:proxyObj.getClass();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        proxyObj = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Class.forName(interfaceName)}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                logger.debug("invoke method ..."+method.getName());
                logger.debug("invoke method before...."+System.currentTimeMillis());
                Object result = method.invoke(target,args);
                return result;
            }
        });
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }
}
