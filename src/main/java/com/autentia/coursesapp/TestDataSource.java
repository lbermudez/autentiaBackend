package com.autentia.coursesapp;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class TestDataSource {			
	public static DataSource getDataSource() {
		DataSource dataSource = createDataSource();
		DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
		return dataSource;
	}
	private static DatabasePopulator createDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("schemaTest.sql"));
        return databasePopulator;
    }

    private static BasicDataSource createDataSource() {
    	BasicDataSource basicDataSource = new BasicDataSource();
    	basicDataSource.setDriverClassName("org.h2.Driver");
    	basicDataSource.setUrl("jdbc:h2:mem:testdb;MODE=MYSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
    	basicDataSource.setUsername("sa");
    	basicDataSource.setPassword("");
        return basicDataSource;      
    }	
}
