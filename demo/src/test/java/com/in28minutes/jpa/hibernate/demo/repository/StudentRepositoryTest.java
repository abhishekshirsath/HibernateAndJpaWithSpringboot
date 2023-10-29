package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Address;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	public void setAddressDetails() {
		Student student = em.find(Student.class, 20001);
		student.setAddress(new Address("no 101", "some Street", "Pune"));
		
		em.flush();
		//student.getPassport();
		
		logger.info("student -:: {}",student);
		
		logger.info("Passport-:: {}",student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001);
		logger.info("student -:: {}",student);
		
		//student.getPassport();
		logger.info("Passport-:: {}",student.getPassport());
	}
	
	@Test
	//@Transactional
	public void someTest() {
	repository.someDummyOperation();
	}

	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudentDetails() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -:: {}",passport);
		
		//student.getPassport();
		logger.info("student-:: {}",passport.getStudent());
	}
	
	//ManyToMany()
	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);
		
		logger.info("student -:: {}",student);
		logger.info("course , {}", student.getCourses());
	
	}
	
	@Test
	@Transactional
	public void retrieveCoursesAndStudent() {
		Course course = em.find(Course.class, 10001L);
		
		logger.info("courses details -:: {}",course);
		logger.info("Students Details with associated Course: , {}", course.getStudents());
	
	}

}
