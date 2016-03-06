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

import com.autentia.coursesapp.dao.CourseDao;
import com.autentia.coursesapp.dao.TeacherDao;
import com.autentia.coursesapp.dao.impl.CourseDaoImpl;
import com.autentia.coursesapp.dao.impl.TeacherDaoImpl;

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
	
	@Bean
	public TeacherDao teacherDao() {
		return new TeacherDaoImpl();
	}
	
	@Bean
	public CourseDao courseDao() {
		return new CourseDaoImpl();
	}

	public static void main(String[] args) {
		new CoursesApp().configure(new SpringApplicationBuilder(CoursesApp.class)).run(args);
	}

}