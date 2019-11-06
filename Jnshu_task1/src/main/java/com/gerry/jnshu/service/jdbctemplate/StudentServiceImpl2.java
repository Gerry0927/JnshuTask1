package com.gerry.jnshu.service.jdbctemplate;

import com.gerry.jnshu.bean.Student;
import com.gerry.jnshu.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceImpl2 implements StudentService {
    private Logger logger = LogManager.getLogger(StudentServiceImpl2.class.getSimpleName());

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> queryById(long id) {
        logger.info("-------->1");
        String sql = "SELECT id,create_time,update_time,name,qq,jnshu_type,join_time,school,online_num,daily_url,slogan,counsellor,known_path FROM student WHERE id = ?";
        List<Student> student = jdbcTemplate.query(sql, new StudentMapper(), id);
        return student;
    }


    @Override
    public long insertInfo(final Student student) {
        final String sql = "INSERT INTO student(name,qq,jnshu_type,join_time,school,online_num,daily_url,slogan,counsellor,known_path) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";
        //long createAt = student.getCreate_at();
        //long updateAt = student.getUpdate_at();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String name = student.getName();
                String qq = student.getQq();
                String profession = student.getJnshuType();
                String joinAt = student.getJoinTime();
                String dailyUrl = student.getDailyUrl();
                String onlineNum = student.getOnlineNum();
                String counselor = student.getCounsellor();
                String school = student.getSchool();
                String slogan = student.getSlogan();
                String knowPath = student.getKnownPath();

                PreparedStatement preState = con.prepareStatement(sql);
                preState.setString(1, name);
                preState.setString(2, qq);
                preState.setString(3, profession);
                preState.setString(4, joinAt);
                preState.setString(5, dailyUrl);
                preState.setString(6, onlineNum);
                preState.setString(7, counselor);
                preState.setString(8, school);
                preState.setString(9, slogan);
                preState.setString(10, knowPath);
                return preState;
            }

        }, keyHolder);
        return keyHolder.getKey().longValue();
    }


    @Override
    public boolean deleteById(long id) {
        String sql  ="delete * from student where id=?";
        int rowId = jdbcTemplate.update(sql,id);
        if(rowId>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateSloganById(String slogan, long id) {
        String sql =  "UPDATE student SET slogan =? WHERE ID = ?";
        int rowId = jdbcTemplate.update(sql,slogan,id);
        if(rowId>0){
            return true;
        }
        return false;
    }

    class StudentMapper implements RowMapper<Student> {

        public Student mapRow(ResultSet rs, int i) throws SQLException {
            Student student = new Student();
            if (rs != null) {
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setQq(rs.getString("qq"));
                student.setJnshuType(rs.getString("jnshu_type"));
                student.setJoinTime(rs.getString("join_time"));
                student.setDailyUrl(rs.getString("daily_url"));
                student.setOnlineNum(rs.getString("online_num"));
                student.setCounsellor(rs.getString("counsellor"));
                student.setSchool(rs.getString("school"));
                student.setSlogan(rs.getString("slogan"));
                student.setKnownPath(rs.getString("known_path"));
                student.setCreateTime(rs.getLong("create_time"));
                student.setUpdateTime(rs.getLong("update_time"));
            }
            return student;
        }
    }
}
