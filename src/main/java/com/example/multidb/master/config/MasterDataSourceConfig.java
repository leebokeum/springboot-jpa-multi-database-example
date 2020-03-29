package com.example.multidb.master.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "masterEntityManagerFactory",
        transactionManagerRef = "masterTransactionManager",
        basePackages = { "com.example.multidb.master.repository" })
@MapperScan(basePackages="com.example.multidb.master.repository.mybatis", sqlSessionFactoryRef="masterSessionFactory")
public class MasterDataSourceConfig {

    private final DataSource masterDataSource;

    @Autowired
    public MasterDataSourceConfig(@Qualifier("masterDataSource")DataSource masterDataSource) { //@Qualifier 사용할 의존 객체를 선택할 수 있도록 해준다.
        this.masterDataSource = masterDataSource;
    }

    @Primary
    @Bean(name = "masterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(masterDataSource)
                .packages("com.example.multidb.master.domain")
                .persistenceUnit("master")
                .build();
    }

    @Primary
    @Bean(name = "masterTransactionManager")
    public PlatformTransactionManager masterTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(masterEntityManagerFactory(builder).getObject());
    }

    /* -----------------mybatis 셋팅------------------------------------- */
    @Primary
    @Bean(name = "masterSessionFactory")
    public SqlSessionFactory masterSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/master/*.xml"));
        return sessionFactoryBean.getObject();
    }


    @Primary
    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier( "masterSessionFactory") SqlSessionFactory masterSessionFactory) {
        return new SqlSessionTemplate(masterSessionFactory);
    }
}
