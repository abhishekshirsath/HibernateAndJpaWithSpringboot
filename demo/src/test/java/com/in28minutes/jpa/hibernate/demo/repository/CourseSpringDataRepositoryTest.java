package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.springframework.data.domain.Sort;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository repository;
	
	@Test
	public void findById_course_present() {
		Optional<Course> courseOptional = repository.findById(10001L);
		
		assertTrue(courseOptional.isPresent());
		
		//logger.info("10001L, :{}",courseOptional.isPresent());
	}
	
	@Test
	public void findById_course_not_present() {
		Optional<Course> courseOptional = repository.findById(20001L);
		
		assertFalse(courseOptional.isPresent());
		//logger.info("20001L,:{}",courseOptional.isPresent());
	}
	
	@Test
	public void playAroundWithSpringDataRepository() {
		
		/*
		 * Course course = repository.save(new Course("Microservices in 100 steps"));
		 * repository.save(course);
		 * 
		 * course.setName("Microservices in 100 steps - updated");
		 * repository.save(course);
		 */
		
		logger.info("Courses : {}",repository.findAll());
		//Courses : [Course [name=JPA in 50 steps], Course [name=Spring boot in 50 steps], Course [name=Jenkins in 50 steps]]
		
		logger.info("Count : {}",repository.count());
		//Count : 3
		
	}
	
	@Test
	public void sort() {
		
		//Sort sort = new Sort(Sort.Direction.DESC, "name").and(null);
		
		//logger.info("Sorted Courses : {}",repository.findAll(sort));
		
	}
	
	@Test
	public void pagination() {
		
		PageRequest pageRequest = PageRequest.of(0, 3);
		
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("first page: {}",firstPage.getContent());
		
		Pageable secondPagable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPagable);
		logger.info("Second Page: {}",secondPage.getContent());
	}

	@Test 
	public void findUsingName() {
		logger.info("Find By name: {}",repository.findByName("dummy1"));
	}
	
	
	

}
