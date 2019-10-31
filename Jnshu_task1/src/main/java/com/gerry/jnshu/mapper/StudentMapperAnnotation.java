package com.gerry.jnshu.mapper;

import com.gerry.jnshu.bean.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapperAnnotation {

    @Select("SELECT * FROM student WHERE id = #{id}")
    List<Student> queryStudentById(long id);

    @Insert("INSERT INTO student(name,qq,jnshu_type,join_time,school,online_num,daily_url,slogan,counselor,known_path)" +
            "VALUES (#{name},#{qq},#{jnshuType},#{joinTime},#{school},#{onlineNum},#{dailyUrl},#{slogan},#{counsellor},#{knownPath})")
    long insertStudentInfo(Student student);

    @Update("UPDATE student SET slogan = #{param1} WHERE ID = #{param2}")
    int deleteStudentById(long id);

    @Delete("DELETE FROM student WHERE  id = #{id}")
    int updateSloganById(String slogan, long id);

}