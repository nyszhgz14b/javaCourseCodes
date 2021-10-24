package com.example.week05.task10;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class HikariDemo {
    private static HikariDataSource instance;
    public static final HikariDataSource getInstance(){
        if(instance==null){
            instance= new MyHikariConfig().dataSource();
        }
        return instance;
    }
    /**
     * 插入数据库
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void inster() throws ClassNotFoundException, SQLException {
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into student (name) values(?) ");
        preparedStatement.setString(1,"test");
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }

    /**
     * 更新数据
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void update() throws ClassNotFoundException, SQLException {
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into student (name) values(?) ");
        preparedStatement.setString(1,"wangwu");
        preparedStatement.execute();
        preparedStatement.clearParameters();
        PreparedStatement statement = connection.prepareStatement("update  student set name=? where name = ? ");
        statement.setString(1,"list");
        statement.setString(2,"wangwu");
        statement.executeUpdate();
        statement.clearParameters();
        statement.close();
        connection.close();
    }
    public void query() throws ClassNotFoundException, SQLException {
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into student (name) values(?)");
        preparedStatement.setString(1,"wangwu");
        preparedStatement.execute();
        preparedStatement.clearParameters();
        PreparedStatement statement = connection.prepareStatement("select * from student where name = ?");
        statement.setString(1,"wangwu");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id")+"-----"+resultSet.getString("name"));
        }
        statement.close();
        connection.close();
    }
    public void delete() throws ClassNotFoundException, SQLException {
        Connection connection = instance.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into student (name) values(?)");
        preparedStatement.setString(1,"wangwu");
        preparedStatement.execute();
        PreparedStatement statement = connection.prepareStatement("delete from student where name = ?");
        statement.setString(1,"wangwu");
        statement.execute();
        statement.close();
        connection.close();
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        StatementDemo statementDemo = new StatementDemo();
        statementDemo.inster();
        statementDemo.query();
        statementDemo.update();
        statementDemo.query();
        statementDemo.delete();
        statementDemo.query();

    }
}
