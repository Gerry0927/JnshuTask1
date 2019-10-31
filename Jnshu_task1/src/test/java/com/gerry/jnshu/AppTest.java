package com.gerry.jnshu;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.sql.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testQuery(){
//        for (int i = 0; i < 1000; i++) {
//            executeQuery();
//        }
        executeQuery();
//        executeInsert();

    }

    private void executeInsert(){
        Connection connection  =getConn();
        ResultSet rs = null;
        Statement statement = null;
        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
                //2.创建statement类对象，用来执行SQL语句！！
                statement = connection.createStatement();

                //要执行的SQL语句
                String sql = "insert into student(name,qq,jnshu_type,online_num,daily_url,counsellor) values('test','948212712','css','css_007','http://xxxxx.html','test')";
                //3.ResultSet类，用来存放获取的结果集！！
                boolean flag = statement.execute(sql);
                System.out.println("插入结果："+flag);

            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private void executeQuery()  {
        Connection connection = getConn();
        ResultSet rs = null;
        Statement statement = null;
        try {
            if(connection!=null&&!connection.isClosed()){
                System.out.println("Succeeded connecting to the Database!");
                //2.创建statement类对象，用来执行SQL语句！！
                statement = connection.createStatement();
                //要执行的SQL语句
                String sql = "select * from student";
                //3.ResultSet类，用来存放获取的结果集！！
                rs= statement.executeQuery(sql);
                String address = null;
                String name = null;

                while(rs.next()){
                    //获取sname这列数据
                    name = rs.getString("name");
                    //获取address这列数据
                    address = rs.getString("school");
                    //输出结果
                    System.out.println(name + "\t" + address);

                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally{
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    private Connection getConn()  {
        //声明Connection对象
        Connection conn=null;
        //驱动程序名
        String driver = "com.mysql.cj.jdbc.Driver";
        //URL指向要访问的数据库名studb
        String url = "jdbc:mysql://localhost:3306/jnshu";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "zhou1990";
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
}
