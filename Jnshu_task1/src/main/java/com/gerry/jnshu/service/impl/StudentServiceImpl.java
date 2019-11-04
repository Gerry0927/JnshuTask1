package com.gerry.jnshu.service.impl;

import com.gerry.jnshu.bean.Student;
import com.gerry.jnshu.mapper.StudentMapper;
import com.gerry.jnshu.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

    Logger logger = LogManager.getLogger(StudentServiceImpl.class.getSimpleName());
    @Resource
    private StudentMapper studentMapper;
    public StudentServiceImpl() {
    }
    public List<Student> queryById(long id) {
        logger.info("-------->"+id);
        return studentMapper.queryStudentById(id);
    }

    @Override
    public List<Student> queryByName(String name){
        return studentMapper.queryStudentByName(name);
    }

    public long insertInfo(Student student) throws Exception{
        return studentMapper.insertStudentInfo(student);
    }

    @Transactional
    public int insertInfos(List<Student> stuInfos) throws Exception {
        return studentMapper.insertStudentInfos(stuInfos);
    }

    public boolean deleteById(long id) {
        return studentMapper.deleteStudentById(id);
    }
    public boolean updateSloganById(String slogan, long id) {
        return studentMapper.updateSloganById(slogan,id);
    }
}
