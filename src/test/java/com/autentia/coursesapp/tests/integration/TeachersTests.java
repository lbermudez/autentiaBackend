package com.autentia.coursesapp.tests.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.autentia.coursesapp.tests.integration.config.CoursesAppConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CoursesAppConfigTest.class)
@WebIntegrationTest(randomPort = true)
public class TeachersTests {
	
	@Value("${local.server.port}")
	private Integer port;
	private static final Logger log = LoggerFactory.getLogger(TeachersTests.class);
	private RestTemplate restTemplate = new TestRestTemplate();	
	
	@Test
	public void getTeachers() {
		ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl().concat("/teachers/"), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	private String getBaseUrl() {
		return "http://localhost:".concat(port.toString());
	}
}
