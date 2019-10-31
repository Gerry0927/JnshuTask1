package com.gerry.jnshu.service;

import com.gerry.jnshu.bean.Student;

import java.util.List;

public interface StudentService {
    List<Student> queryById(long id);
    List<Student> queryByName(String name) throws Exception;
    long insertInfo(Student student) throws Exception;
    boolean deleteById(long id);
    boolean updateSloganById(String slogan, long id);
}
