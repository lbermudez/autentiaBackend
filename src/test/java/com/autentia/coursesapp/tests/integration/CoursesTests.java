package com.autentia.coursesapp.tests.integration;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

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

import com.autentia.coursesapp.CoursesAppConfig;
import com.autentia.coursesapp.model.Course;
import com.autentia.coursesapp.model.Level;
import com.autentia.coursesapp.model.Teacher;
import com.autentia.coursesapp.restservices.Courses;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CoursesAppConfig.class)
@WebIntegrationTest(randomPort = true)
public class CoursesTests {

	@Value("${local.server.port}")
	private Integer port;
	private static final Logger log = LoggerFactory.getLogger(CoursesTests.class);
	private RestTemplate restTemplate = new TestRestTemplate();
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Test
	public void countCoursesActives() {
		ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl().concat("courses/count/").concat(Courses.ACTIVES.toString()), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void createCourse() {
		ResponseEntity<String> response = createCourse("JEE", 50, Teacher.getInstance(1), Level.AVANZADO, true);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void createAndCheckOneMore() throws JsonParseException, JsonMappingException, IOException {
		List<Course> currentCourses = getCourses(Courses.ACTIVES, Courses.ASC_SORT);
		int size1 = currentCourses.size();
		log.debug("Courses.size: " + size1);

		createCourse("JAVA 8", 50, Teacher.getInstance(1), Level.AVANZADO, true);
		currentCourses = getCourses(Courses.ACTIVES, Courses.ASC_SORT);
		int size2 = currentCourses.size();
		log.debug("Courses.size: " + size2);

		assertEquals(size1 + 1, size2);
	}

	@Test
	public void getCoursesWithWrongSortParam() throws JsonParseException, JsonMappingException, IOException {
		String pathParams = "/courses/".concat(Courses.ACTIVES.toString()).concat("/").concat("ascc");
		ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl().concat(pathParams), String.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	@Test
	public void createCourseWithNotExistsTeacher() throws JsonParseException, JsonMappingException, IOException {
		ResponseEntity<String> response = createCourse("NodeJs", 70, Teacher.getInstance(3), Level.AVANZADO, true);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void getTeachers() {
		ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl().concat("/teachers/"), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void getCoursesByPage() throws JsonParseException, JsonMappingException, IOException {
		String pathParams = "/courses/".concat(Courses.ACTIVES.toString()).concat("/").concat(Courses.ASC_SORT).concat("/1/3");
		ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl().concat(pathParams), String.class);
		List<Course> courses = MAPPER.readValue(response.getBody(), new TypeReference<List<Course>>() {});
		assertEquals(3, courses.size());
	}

	private List<Course> getCourses(Boolean active, String sort)
			throws IOException, JsonParseException, JsonMappingException {
		String pathParams = "/courses/".concat(active.toString()).concat("/").concat(sort);
		ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl().concat(pathParams), String.class);
		List<Course> currentCourses = MAPPER.readValue(response.getBody(), new TypeReference<List<Course>>() {});
		return currentCourses;
	}

	private ResponseEntity<String> createCourse(String title, Integer hours, Teacher teacher, Level level,
			Boolean active) {
		Course course = new Course(title, hours, teacher, level, active);
		ResponseEntity<String> response = restTemplate.postForEntity(getBaseUrl().concat("/courses"), course,
				String.class);
		return response;
	}

	private String getBaseUrl() {
		return "http://localhost:".concat(port.toString());
	}
}
