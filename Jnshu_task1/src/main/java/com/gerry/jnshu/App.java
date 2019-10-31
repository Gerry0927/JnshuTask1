package com.gerry.jnshu;

import com.gerry.jnshu.bean.Student;
import com.gerry.jnshu.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    @Configuration
    @ComponentScan(basePackages="com.gerry.jnshu")
    @EnableAspectJAutoProxy
    public class SpringConfiguration {
    }


    public static void main( String[] args ) throws Exception {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        StudentService studentService = (StudentService) applicationContext.getBean("studentService");
//        List<Student> studentInfos = studentService.queryById(6);
//        System.out.println("studentInfos size---->" + studentInfos.size());
//        System.out.println("student info---->"+studentInfos.get(0).toString());

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2.从IOC中获取Beab实例
//
//        Student helloWorld = (Student) ctx.getBean("student");
//        //HelloWorld helloWorld =  ctx.getBean(HelloWorld.class);
//
//        Object school = ctx.getBean("factoryBeanPojo");
//        FactoryPojo pojo = (FactoryPojo) ctx.getBean("&factoryBeanPojo");
//        System.out.println(school.getClass().getName());
//        System.out.println(pojo.getObjectType());
//

        Student student = new Student();
        student.setName("zhangsan");
        StudentService studentService = (StudentService) ctx.getBean("studentService");
        studentService.insertInfo(student);

    }



}
