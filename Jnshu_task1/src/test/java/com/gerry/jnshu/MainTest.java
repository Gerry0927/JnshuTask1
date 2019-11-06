package com.gerry.jnshu;

import com.gerry.jnshu.bean.Student;
import com.gerry.jnshu.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MainTest {
    private static final Logger logger = LogManager.getLogger(MainTest.class);

    @Resource(name = "studentService")
    StudentService studentService;

    @Test
    public void queryStudent() {
        List<Student> students = studentService.queryById(1);
        if (students.size() > 0) {
            logger.info("查询结果----->" + students.get(0));
        } else {
            logger.info("查询结果为空");
        }
    }
    @Test
    public void queryStudentByName() {
        long start = System.currentTimeMillis();
        List<Student> students = null;
        try {
            Thread.sleep(30000);
//            students = studentService.queryByName("高世豪999852");
            if (students.size() > 0) {
                logger.info("查询结果----->" + students.get(0));
            } else {
                logger.info("查询结果为空");
            }
            long end = System.currentTimeMillis();
            logger.info("name 字段索引查询（100万）耗时：" + (end - start));
        } catch (Exception e) {
            logger.info("查询出现异常"+e.getCause());
           e.printStackTrace();
        }

    }


    @Test
    public void insertStudent() throws InterruptedException {
        //插入 100 万条数据
        insert300w();

    }


    public void insert300w() throws InterruptedException {
        //ExecutorService executorService = Executors.newFixedThreadPool(1000);
        //声明等待锁
//        final CountDownLatch latch = new CountDownLatch(1);

//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {

        long start = System.currentTimeMillis();
//       for (int i = 0; i < 30; i++) {
        final List<Student> studentInfos = new ArrayList<>();
        for (int j = 0; j < 1000000; j++) {
            Student student = new Student();
            student.setName("高世豪" + j);
            student.setJnshuType("JavaWeb");
            student.setOnlineNum("007");
            student.setDailyUrl("http://www/test.com");
            student.setCounsellor("令狐冲");
//          try {
//                studentService.insertInfo(student);
//          } catch (Exception e) {
//                e.printStackTrace();
//          }
            synchronized (student) {
                studentInfos.add(student);
//             }
            }
            try {
//                studentService.insertInfos(studentInfos);
            } catch (Exception e) {
                e.printStackTrace();
            }
//          try {
//               logger.debug("要插入的List长度"+studentInfos.size());
//               studentService.insertInfos(studentInfos);
//           } catch (Exception e) {
//                e.printStackTrace();
//           }
//          latch.countDown();
            long end = System.currentTimeMillis();
            logger.debug("插入 100 万条数据耗时：" + (end - start));
            //}
            //});
            //开始等待，主线程挂起
//        latch.await();
        }
    }
}
