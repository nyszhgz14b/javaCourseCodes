package com.example.week05.task10;

import java.sql.*;

public class StatementDemo {
    private String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useSSL=false";
    private String DB_USER = "root";
    private String DB_PASSWORD = "12345678";

    /**
     * 插入数据库
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void inster() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement();
        statement.execute("insert into student (name) values('test') ");
        statement.close();
        connection.close();
    }

    /**
     * 更新数据
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void update() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement();
        statement.execute("insert into student (name) values('wangwu') ");
        statement.executeUpdate("update  student set name='lisi' where name = 'wangwu' ");
        statement.close();
        connection.close();
    }
    public void query() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement();
        statement.execute("insert into student (name) values('wangwu') ");
        ResultSet resultSet = statement.executeQuery("select * from student ");
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id")+"-----"+resultSet.getString("name"));
        }
        statement.close();
        connection.close();
    }
    public void delete() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
        Statement statement = connection.createStatement();
        statement.execute("insert into student (name) values('wangwu') ");
        statement.execute("delete from   student  where name = 'wangwu' ");
        statement.close();
        connection.close();
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        StatementDemo statementDemo = new StatementDemo();
        //statementDemo.inster();
       // statementDemo.update();
        statementDemo.delete();
         statementDemo.query();

    }
}
