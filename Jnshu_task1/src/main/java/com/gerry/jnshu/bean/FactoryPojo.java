package com.gerry.jnshu.bean;

import org.springframework.beans.factory.FactoryBean;

public class FactoryPojo implements FactoryBean {

    private String type;
    @Override
    public Object getObject() throws Exception {
        if(type!=null&&type.equals("student")){
            return new Student();
        }
        return new School();
    }

    @Override
    public Class<?> getObjectType() {
        return School.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
