package com.uberrent.core.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.uberrent.core.repository")
public class DataBaseConfig {

    @Value("#{appProperties['database.username']}")
    private String userName;
    @Value("#{appProperties['database.name']}")
    private String database;
    @Value("#{appProperties['database.url']}")
    private String url;
    @Value("#{appProperties['database.port']}")
    private String port;
    @Value("#{appProperties['database.password']}")
    private String password;
    @Value("#{appProperties['database.driverName']}")
    private String driver;

    @Profile({"test","stage","prod"})
    @Bean(name = "flyway", initMethod = "migrate")
    public Flyway getupFlyway(@Autowired DataSource dataSource){
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("classpath:db/migration/");
        flyway.setDataSource(dataSource);
        return flyway;
    }

    @Profile({"dev","unit"})
    @Bean(name = "flyway", initMethod = "validate")
    public Flyway setupFlyway(@Autowired DataSource dataSource){
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations("classpath:db/migration/");
        flyway.setDataSource(dataSource);
        return flyway;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl("jdbc:postgresql://"+url+":"+port+"/"+database);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
//      dataSource.setValidationQuery(databaseValidationQuery);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        return dataSource;
    }


    @Bean(name="entityManagerFactory")
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean getFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = setUpLocalContainerEntityManagerFactoryBean();
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.spatial.dialect.postgis.PostgisDialect");
        props.put("hibernate.hbm2ddl.auto", "validate");
//        props.put("hibernate.physical_naming_strategy", "io.ascending.training.extend.hibernate.ImprovedNamingStrategy");
        props.put("hibernate.connection.charSet", "UTF-8");
        props.put("hibernate.show_sql", "true");
//        props.put("org.hibernate.flushMode", "ALWAYS");
        factoryBean.setJpaProperties(props);
        return factoryBean;
    }

    @Bean(name ="transactionManager")
    public PlatformTransactionManager getTransactionManager(@Autowired EntityManagerFactory factory, @Autowired DataSource dataSource){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory);
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }


    private LocalContainerEntityManagerFactoryBean setUpLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan(new String[]{"com.uberrent.core.domain", "com.uberrent.core.repository"});
        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return factoryBean;
    }
}
