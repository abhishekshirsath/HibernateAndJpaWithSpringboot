package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_basic() {
		List resultList = em.createNamedQuery("query_get_all_courses").getResultList();
		logger.info("Select c from Course c {}",resultList);
		// Select c from Course c [Course [name=Learn CI/CD], Course [name=Learn AngulerJS - Updated], Course [name=JPA in 50 steps], Course [name=Spring boot in 50 steps], Course [name=Jenkins in 50 steps]]
		
	}
	
	@Test
	public void jpql_Typed() {
		 TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses",Course.class);
		 List<Course> resultList = query.getResultList();
		 logger.info("Select c from Course c {}",resultList);
		
	}
	
	@Test
	public void jpql_Where() {
		 TypedQuery<Course> query = em.createQuery("query_get_50_step_course",Course.class);
		 List<Course> resultList = query.getResultList();
		 logger.info("Select c from Course c {}",resultList);
		 
		 //Select c from Course c [Course [name=JPA in 50 steps], Course [name=Spring boot in 50 steps], Course [name=Jenkins in 50 steps]]
		
	}
	
	@Test
	public void jpql_course_without_student() {
		 TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty",
				 Course.class);
		 List<Course> resultList = query.getResultList();
		 logger.info("Results is: {}",resultList);
		 
		 //Results is: [Course [name=Spring boot in 50 steps]]
	}
	
	@Test
	public void jpql_course_with_atleat_2_student() {
		 TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >=2",
				 Course.class);
		 List<Course> resultList = query.getResultList();
		 logger.info("Results is: {}",resultList);
		 
		 //Results is: [Course [name=JPA in 50 steps]]
	}
	
	@Test
	public void jpql_course_ordered_by_student() {
		 TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students)",
				 Course.class);
		 List<Course> resultList = query.getResultList();
		 logger.info("Results is: {}",resultList);
		 
		 //Results is: [Course [name=Spring boot in 50 steps], Course [name=Jenkins in 50 steps], Course [name=JPA in 50 steps]]
	}
	
	@Test
	public void jpql_course_ordered_by_descending_student() {
		 TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc",
				 Course.class);
		 List<Course> resultList = query.getResultList();
		 logger.info("Results is: {}",resultList);
		 
		 //Results is: [Course [name=JPA in 50 steps], Course [name=Jenkins in 50 steps], Course [name=Spring boot in 50 steps]]
	}
	
	@Test
	public void jpql_student_with_password_in_a_certain_patterns() {
		 TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%1234%' ",
				 Student.class);
		 List<Student> resultList = query.getResultList();
		 logger.info("Results is: {}",resultList);
		 
		 //Results is: [Student[Ranga], Student[Adam]]
	}
	
	// like 
	// BETWEEN 100 TO 1000
	// IS NULL
	// upper, lower, trim , length 
	
	// JOIN -> Select c,s  from Course c JOIN c.students s  
	// LEFT JOIN -> Select c,s  from Course c LEFT JOIN c.students s  
	// CROSS JOIN-> Select c,s  from Course c, Student s
	
	@Test
	public void join() {
		
		Query query = em.createQuery("Select c,s  from Course c JOIN c.students s");
		
		List<Object[]> resultList = query.getResultList();
		
		logger.info("Results Size {}",resultList.size());
		
		for(Object[] result:resultList) {
			
			logger.info("Course Details:{} Student Details {}",result[0],result[1]);
			
		}
		
	}
	
	//Results Size 4
	//Course Details:Course [name=JPA in 50 steps] Student Details Student[Ranga]
	//Course Details:Course [name=JPA in 50 steps] Student Details Student[Adam]
	//Course Details:Course [name=JPA in 50 steps] Student Details Student[Jane]
	//Course Details:Course [name=Jenkins in 50 steps] Student Details Student[Ranga]
	
	
	@Test
	public void leftJoin() {
		
		Query query = em.createQuery("Select c,s  from Course c LEFT JOIN c.students s");
		
		List<Object[]> resultList = query.getResultList();
		
		logger.info("Results Size {}",resultList.size());
		
		for(Object[] result:resultList) {
			
			logger.info("Course Details:{} Student Details {}",result[0],result[1]);
			
		}
		
	}
	
	/*
	 * Results Size 5 Course Details:Course [name=JPA in 50 steps] Student Details
	 * Student[Ranga] Course Details:Course [name=JPA in 50 steps] Student Details
	 * Student[Adam] Course Details:Course [name=JPA in 50 steps] Student Details
	 * Student[Jane] Course Details:Course [name=Spring boot in 50 steps] Student
	 * Details null Course Details:Course [name=Jenkins in 50 steps] Student Details
	 * Student[Ranga]
	 */
	
	@Test
	public void cross_Join() {
		
		Query query = em.createQuery("Select c,s  from Course c, Student s");
		
		List<Object[]> resultList = query.getResultList();
		
		logger.info("Results Size {}",resultList.size());
		
		for(Object[] result:resultList) {
			
			logger.info("Course Details:{} Student Details {}",result[0],result[1]);
			
		}
		
	}
	/*
	 * Results Size 9 Course Details:Course [name=JPA in 50 steps] Student Details
	 * Student[Ranga] Course Details:Course [name=JPA in 50 steps] Student Details
	 * Student[Adam] Course Details:Course [name=JPA in 50 steps] Student Details
	 * Student[Jane] Course Details:Course [name=Spring boot in 50 steps] Student
	 * Details Student[Ranga] Course Details:Course [name=Spring boot in 50 steps]
	 * Student Details Student[Adam] Course Details:Course [name=Spring boot in 50
	 * steps] Student Details Student[Jane] Course Details:Course [name=Jenkins in
	 * 50 steps] Student Details Student[Ranga] Course Details:Course [name=Jenkins
	 * in 50 steps] Student Details Student[Adam] Course Details:Course
	 * [name=Jenkins in 50 steps] Student Details Student[Jane]
	 */
	

}
