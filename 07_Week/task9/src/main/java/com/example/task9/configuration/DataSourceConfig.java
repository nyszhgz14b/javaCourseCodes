package com.example.task9.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.task9.router.DataSourceRouter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {
    @Bean("dbMaster")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dbMaster(){
        return new DruidDataSource();
    }
    @Bean("dbSavle")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource dbSavle(){
        return new DruidDataSource();
    }
    @Primary
    @Bean(name = "dataSourceRouter")
    public DataSource dataSourceRouter(@Qualifier("dbMaster") DataSource master,@Qualifier("dbSavle") DataSource slave){
        DataSourceRouter dataSourceRouter = new DataSourceRouter();
        Map<Object, Object> map = new HashMap<>(5);
        map.put("master", master);
        map.put("slave", slave);

        // master 作为默认数据源
        dataSourceRouter.setDefaultTargetDataSource(master);
        dataSourceRouter.setTargetDataSources(map);
        return dataSourceRouter;
    }
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("dataSourceRouter") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
