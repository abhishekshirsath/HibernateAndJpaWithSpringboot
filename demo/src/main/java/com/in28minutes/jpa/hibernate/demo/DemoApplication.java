package com.in28minutes.jpa.hibernate.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.PartTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.demo.repository.EmployeeRepository;
import com.in28minutes.jpa.hibernate.demo.repository.StudentRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		/*
		Course course = repository.findById(10001L);
		 
		logger.info("Details of Course ==>{}",course);
		//repository.deleteById(10001L);
		
		repository.save(new Course("Microservices in 100 steps"));
		repository.save(new Course("Junit in 100 steps"));
		repository.save(new Course("MOckito in 100 steps-updated"));
		*/
		
		//repository.playWithEntityManager();
		
		//studentRepository.saveStudentWithPassport();
		//courseRepository.addHardCodedReviewsForCourse();
		
		/*
		 * List<Review> reviews = new ArrayList(); reviews.add(new
		 * Review("5","Great Hands On Studff")); reviews.add(new
		 * Review("5","Hats off"));
		 * 
		 * courseRepository.addReviewsForCourse(10003L, reviews);
		 */
		
		//studentRepository.insertStudentAndCourseHardCoded();
		//studentRepository.insertStudentAndCourse(new Student("Jack1"), new Course("Microservices"));
		/*
		employeeRepository.insert(new FullTimeEmployee("Jack",new BigDecimal("50")));
		
		employeeRepository.insert(new PartTimeEmployee("Jill",new BigDecimal("100")));
		
		logger.info("FullTimeEmployee employee {}",employeeRepository.retrieveAllFullTimeEmployee());
		
		logger.info("PartTimeEmployee employee {}",employeeRepository.retrieveAllPartTimeEmployee());
		
		*/
		
		
	}

}
