package com.autentia.coursesapp;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CoursesAppConfig extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(CoursesAppConfig.class);
	private static String profile = null;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CoursesAppConfig.class);
	}

	@Bean	
	public DataSource getDataSource() {
		if (PROFILE_DEV()) {
			return DevDataSource.getDataSource();			
		} else {
			return TestDataSource.getDataSource();
		}
	}

	private boolean PROFILE_DEV() {
		return (null != profile && profile.equals("dev"));
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
		profile = args[0];
		new CoursesAppConfig().configure(new SpringApplicationBuilder(CoursesAppConfig.class)).run(args);
	}

}