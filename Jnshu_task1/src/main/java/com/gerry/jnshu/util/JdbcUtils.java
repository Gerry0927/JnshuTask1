package com.gerry.jnshu.util;

import java.sql.*;

public class JdbcUtils {

    public static Connection getConn()  {
        //声明Connection对象
        Connection conn=null;
        //驱动程序名
        String driver = "com.mysql.cj.jdbc.Driver";
        //URL指向要访问的数据库名studb
        String url = "jdbc:mysql://localhost:3306/jnshu";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123456";
        //加载驱动程序
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            System.out.println("无法找到驱动");
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }

        return conn;
    }


    public static void closeConn(Connection connection){
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeResultSet(ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeStatement(Statement statement){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
