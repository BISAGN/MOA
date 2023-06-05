//package com.AyushEdu.config;
//
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class HibernateRepoConfiguration {
//	@Value("${spring.datasourceRepo.driver-class-name}")
//	private String DRIVER;
//
//	@Value("${spring.datasourceRepo.password}")
//	private String PASSWORD;
//
//	@Value("${spring.datasourceRepo.url}")
//	private String URL;
//
//	@Value("${spring.datasourceRepo.username}")
//	private String USERNAME;
//
//	@Value("${hibernateRepo.dialect}")
//	private String DIALECT;
//
//	@Value("${hibernateRepo.show_sql}")
//	private String SHOW_SQL;
//
//	
//	  @Value("${hibernateRepo.hbm2ddl.auto}") private String HBM2DDL_AUTO;
//	 
//
//	@Value("${entitymanagerRepo.packagesToScan}")
//	private String PACKAGES_TO_SCAN;
//
//	@Bean
//	@Qualifier("Repo")
//	public DataSource dataSource2() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(DRIVER);
//		dataSource.setUrl(URL);
//		dataSource.setUsername(USERNAME);
//		dataSource.setPassword(PASSWORD);
//		return dataSource;
//	}
//
//	@Bean
//	@Qualifier("Repo")
//	public LocalSessionFactoryBean sessionFactory2() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource2());
//		sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
//		Properties hibernateProperties = new Properties();
//		hibernateProperties.put("hibernateRepo.dialect", DIALECT);
//		hibernateProperties.put("hibernateRepo.show_sql", SHOW_SQL);
//		hibernateProperties.put("hibernateRepo.hbm2ddl.auto", HBM2DDL_AUTO);
//		sessionFactory.setHibernateProperties(hibernateProperties);
//
//		return sessionFactory;
//	}
//
//	
//	@Bean
//	@Qualifier("Repo")
//	public HibernateTransactionManager transactionManager2() {
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//		transactionManager.setSessionFactory(sessionFactory2().getObject());
//		return transactionManager;
//	}	
//}
