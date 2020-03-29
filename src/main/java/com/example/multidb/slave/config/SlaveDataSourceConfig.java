package com.example.multidb.slave.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "slaveEntityManagerFactory",
        transactionManagerRef = "slaveTransactionManager",
        basePackages = { "com.example.multidb.slave.repository.jpa" })
@MapperScan(basePackages="com.example.multidb.slave.repository.mybatis", sqlSessionFactoryRef="slaveSessionFactory")
public class SlaveDataSourceConfig {
    private final DataSource slaveDataSource;

    @Autowired
    public SlaveDataSourceConfig(@Qualifier("slaveDataSource") DataSource slaveDataSource) { //@Qualifier 사용할 의존 객체를 선택할 수 있도록 해준다.
        this.slaveDataSource = slaveDataSource;
    }

    @Bean(name = "slaveEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(slaveDataSource)
                .packages("com.example.multidb.slave.domain")
                .persistenceUnit("slave")
                .build();
    }

    @Bean("slaveTransactionManager")
    public PlatformTransactionManager slaveTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(slaveEntityManagerFactory(builder).getObject());
    }

    /* -----------------mybatis 셋팅------------------------------------- */
    @Bean(name = "slaveSessionFactory")
    public SqlSessionFactory slaveSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/slave/*.xml"));
        return sessionFactoryBean.getObject();
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier( "slaveSessionFactory") SqlSessionFactory slaveSessionFactory) {
        return new SqlSessionTemplate(slaveSessionFactory);
    }

}
