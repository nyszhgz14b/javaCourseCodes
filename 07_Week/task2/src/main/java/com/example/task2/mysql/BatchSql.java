package com.example.task2.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchSql {
    private static String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/mall?rewriteBatchedStatements=true&characterEncoding=utf-8&useSSL=false";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "12345678";

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        System.out.println("开始时间："+start);
        try {
            Class.forName(DRIVER_CLASS);
            Connection connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("insert into t_user (user_name,cert_no,name,sex,passwd,phone)" +
                    " values (?,?,?,?,?,?)");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 100000; j++) {
                    statement.setString(1,"test"+j);
                    statement.setString(2,j+"");
                    statement.setString(3,"name"+j);
                    statement.setString(4,"1");
                    statement.setString(5,"passwd"+j);
                    statement.setString(6,""+j);
                    statement.addBatch();
                }
                statement.executeBatch();
                connection.commit();
                statement.clearParameters();

            }
            long endTime = System.currentTimeMillis();
            System.out.println("endTime: "+endTime);
            System.out.println(endTime-start);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
