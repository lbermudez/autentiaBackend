package com.autentia.coursesapp.tests.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.autentia.coursesapp.model.Course;
import com.autentia.coursesapp.model.Level;
import com.autentia.coursesapp.model.Teacher;
import com.autentia.coursesapp.tests.integration.config.CoursesAppConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CoursesAppConfigTest.class)
@WebIntegrationTest(randomPort = true)
public class CoursesTests {
	
	@Value("${local.server.port}")
    private Integer port;
	
	private RestTemplate restTemplate = new TestRestTemplate();
	
	@Test
	public void Test() {
		Course course = new Course("JAVA 8", 50, Teacher.getInstance(1), Level.AVANZADO, true);
		ResponseEntity<String> response = restTemplate.postForEntity(getBaseUrl().concat("/courses"), course, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void createCourseTest() {
		Course course = new Course("JAVA 8", 50, Teacher.getInstance(1), Level.AVANZADO, true);
		ResponseEntity<String> response = restTemplate.postForEntity(getBaseUrl().concat("/courses"), course, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void createCourseOneMoreTest() {
		Course course = new Course("JEE", 20, Teacher.getInstance(1), Level.BASICO, true);
		ResponseEntity<String> response = restTemplate.postForEntity(getBaseUrl().concat("/courses"), course, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void createCourseWithNotExistsTeacherTest() {
		Course course = new Course("JAVA 8", 50, Teacher.getInstance(2), Level.AVANZADO, true);
		ResponseEntity<String> response = restTemplate.postForEntity(getBaseUrl().concat("/courses"), course, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	private String getBaseUrl() {
		return "http://localhost:".concat(port.toString());
	}

}
