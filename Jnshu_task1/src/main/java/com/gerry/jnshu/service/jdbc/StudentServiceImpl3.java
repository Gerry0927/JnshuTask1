package com.gerry.jnshu.service.jdbc;

import com.gerry.jnshu.bean.Student;
import com.gerry.jnshu.service.StudentService;
import com.gerry.jnshu.util.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl3 implements StudentService {

    @Override
    public List<Student> queryById(long id) {
        Connection connection = JdbcUtils.getConn();
        ResultSet rs = null;
        Statement statement = null;
        List<Student> studentInfos = new ArrayList<>();
        try {
            if(connection!=null&&!connection.isClosed()){
                System.out.println("Succeeded connecting to the Database!");
                //2.创建statement类对象，用来执行SQL语句！！
                statement = connection.createStatement();
                //要执行的SQL语句
                String sql = "select * from student";
                //3.ResultSet类，用来存放获取的结果集！！
                rs= statement.executeQuery(sql);
                String name = null;
                String qq = null;
                String jnshuType=null;
                String onlineNum=null;
                String dailyUrl=null;
                String counsellor = null;
                while(rs.next()){
                    //获取sname这列数据
                    //name,qq,jnshu_type,online_num,daily_url,counsellor
                    name = rs.getString("name");
                    //获取address这列数据
                    qq = rs.getString("qq");
                    jnshuType = rs.getString("jnshu_type");
                    dailyUrl = rs.getString("daily_url");
                    counsellor = rs.getString("counsellor");
                    //输出结果
                    Student student = new Student();
                    student.setName(name);
                    student.setJnshuType(jnshuType);
                    student.setOnlineNum(onlineNum);
                    student.setDailyUrl(dailyUrl);
                    student.setCounsellor(counsellor);
                    studentInfos.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(statement);
            JdbcUtils.closeConn(connection);
        }
        return studentInfos;
    }


    @Override
    public long insertInfo(Student student) throws Exception {
        Connection connection  =JdbcUtils.getConn();
//        ResultSet rs = null;
        PreparedStatement ps = null;
        int rowNum=0;
        try {
            if (connection != null && !connection.isClosed()) {
                //2.创建statement类对象，用来执行SQL语句！！
                String sql="insert into student(name,qq,jnshu_type,online_num,daily_url,counsellor) values(?,?,?,?,?,?)";
                ps = connection.prepareStatement(sql);
                //要执行的SQL语句
                ps = connection.prepareStatement(sql);
                ps.setString(1,student.getName());
                ps.setString(2,student.getJnshuType());
                ps.setString(3,student.getOnlineNum());
                ps.setString(4,student.getDailyUrl());
                ps.setString(5,student.getCounsellor());
                rowNum = ps.executeUpdate(sql);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.closeStatement(ps);
            JdbcUtils.closeConn(connection);
        }
        return rowNum;
    }


    @Override
    public boolean deleteById(long id) {
        Connection connection  =JdbcUtils.getConn();
//        ResultSet rs = null;
        PreparedStatement ps = null;
        int rowNum=0;
        try {
            if (connection != null && !connection.isClosed()) {
                String sql="delete from student where id=?";
                ps = connection.prepareStatement(sql);
                //要执行的SQL语句
                ps = connection.prepareStatement(sql);
                ps.setLong(1,id);
                rowNum=ps.executeUpdate();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            JdbcUtils.closeStatement(ps);
            JdbcUtils.closeConn(connection);
        }
        return rowNum>0;
    }

    @Override
    public boolean updateSloganById(String slogan, long id) {
        Connection connection  =JdbcUtils.getConn();
//        ResultSet rs = null;
        PreparedStatement ps = null;
        int rowNum=0;
        try {
            if (connection != null && !connection.isClosed()) {
                String sql="update student set slogan=? where id=?";
                ps = connection.prepareStatement(sql);
                //要执行的SQL语句
                ps = connection.prepareStatement(sql);
                ps.setString(1,slogan);
                ps.setLong(2,id);
                rowNum=ps.executeUpdate();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            JdbcUtils.closeStatement(ps);
            JdbcUtils.closeConn(connection);
        }
        return rowNum>0;
    }
}
