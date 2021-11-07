package com.example.task2.mysql;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程处理
 */
public class ThreadPoolBatchSql {
    private static String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/mall?rewriteBatchedStatements=true&characterEncoding=utf-8&useSSL=false";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "12345678";
    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%s").build();
    private static volatile Connection connection =null;
    public static Connection getInstance(){
        if(connection==null){
            synchronized (ThreadPoolBatchSql.class){
                if(connection==null){
                    try {
                        connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
                        connection.setAutoCommit(false);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
    public static void main(String[] args){
        connection = getInstance();
        int processors = Runtime.getRuntime().availableProcessors();
       asyncThread(processors);

    }

    public static void asyncThread(int threadNum){
        final CountDownLatch cdl = new CountDownLatch(threadNum);
        long start = System.currentTimeMillis();
        System.out.println("开始时间："+start);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(threadNum, 15, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50000), threadFactory);
        for (int i = 0; i < threadNum; i++) {
            executor.execute(()->{

                try {

                    PreparedStatement statement = connection.prepareStatement("insert into t_user (user_name,cert_no,name,sex,passwd,phone)" +
                            " values (?,?,?,?,?,?)");
                        for (int j = 0; j < 1000000/threadNum; j++) {
                            //int andIncrement = index.getAndIncrement();
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
                        statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    cdl.countDown();

                }
            });
        }
        try {
            cdl.await();
            long endTime = System.currentTimeMillis();
            System.out.println("endTime: "+endTime);
            System.out.println(endTime-start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
