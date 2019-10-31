package com.gerry.jnshu.mapper;

import com.gerry.jnshu.bean.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
 
    List<Student> queryStudentById(long id);
    List<Student> queryStudentByName(String name);
    long insertStudentInfo(Student student);
     boolean deleteStudentById(long id);
     boolean updateSloganById(String slogan, long id);

 }