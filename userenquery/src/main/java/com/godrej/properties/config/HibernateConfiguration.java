package com.godrej.properties.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {

    @Autowired
    private Environment environment;
   
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.godrej.properties.*" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
    @Bean
    public DataSource dataSource() {
    	BasicDataSource dataSource = new BasicDataSource(); 
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
    	dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
    	dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
    	dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
    	dataSource.setMinIdle(20);
    	dataSource.setMaxIdle(100);
    	dataSource.setMaxOpenPreparedStatements(200);
    	
		/*
		 * DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 * dataSource.setDriverClassName(environment.getRequiredProperty(
		 * "jdbc.driverClassName"));
		 * dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		 * dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		 * dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		 */        return dataSource;
    }
    
//    @Bean(name = "dataSource",destroyMethod="")
//    public DataSource dataSource() {
//        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//        dsLookup.setResourceRef(true);
//        /*return dsLookup.getDataSource("jdbc/eat");*/ 
//       return dsLookup.getDataSource("jdbc/kyc");
//
//    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", "none");
    	properties.put("hibernate.temp.use_jdbc_metadata_defaults", false);	
        return properties;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}

