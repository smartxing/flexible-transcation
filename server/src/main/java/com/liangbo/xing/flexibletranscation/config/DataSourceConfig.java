package com.liangbo.xing.flexibletranscation.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("mysql.jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("ftm.master.jdbc.url"));
        dataSource.setUsername(env.getProperty("ftm.master.jdbc.username"));
        dataSource.setPassword(env.getProperty("ftm.master.jdbc.password"));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("datasource.initialSize")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("datasource.minIdle")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("datasource.maxActive")));
        dataSource.setMaxWait(Long.parseLong(env.getProperty("datasource.maxWait")));
        dataSource.setRemoveAbandoned(Boolean.parseBoolean(env.getProperty("datasource.removeAbandoned")));
        dataSource.setRemoveAbandonedTimeout(Integer.parseInt(env.getProperty("datasource.removeAbandonedTimeout")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env
                .getProperty("datasource.timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env
                .getProperty("datasource.minEvictableIdleTimeMillis")));
        dataSource
                .setPoolPreparedStatements(Boolean.parseBoolean(env.getProperty("datasource.poolPreparedStatements")));
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(env
                .getProperty("datasource.maxPoolPreparedStatementPerConnectionSize")));
        dataSource.setValidationQuery(env.getProperty("datasource.validationQuery"));
        return dataSource;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/sqlmap/*/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


}
