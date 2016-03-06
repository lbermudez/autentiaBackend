package com.autentia.coursesapp;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@SpringBootApplication
@MapperScan("com.autentia.coursesapp.mappers")
public class CoursesApp extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CoursesApp.class);
	}

	@Bean
	public DataSource getDataSource() {		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/courses");
		dataSource.setUsername("root");
		dataSource.setPassword("sisuka12");
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		return sessionFactory.getObject();
	}

	public static void main(String[] args) {
		new CoursesApp().configure(new SpringApplicationBuilder(CoursesApp.class)).run(args);
	}

}