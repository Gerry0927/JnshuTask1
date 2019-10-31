package com.gerry.jnshu.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {

    private SqlSessionFactory sessionFactory;

    public MyBatisUtil() {
        String resource = "mybatis/MybatisConfig.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //创建会话工厂，传入mybatis配置文件的信息
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SqlSession getSqlSession() {
        return sessionFactory.openSession();
    }
}