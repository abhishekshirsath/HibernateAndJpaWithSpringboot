package com.in28minutes.jpa.hibernate.demo.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;

import jakarta.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired	
	EntityManager em;
	
	//findById
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	//Save  = insert + update
	public Student save(Student student) {
		if(student.getId() == null) {
			// insert data
			em.persist(student);
		}else {
			// Update data
			em.merge(student);
		}
		return student;
		
	}
	
	//deleteById
	public void deleteById(Long id) {
		
		Student student =	findById(id);
		em.remove(student);
	}
	
	/*
	public void playWithEntityManager() {
		logger.info("PlayWithEntityManager - Start");
		
		Student student1 = new Student("Learn CI/CD");
		em.persist(student1);
		em.flush();
		
		/*
		Student student2 = new Student("Learn AngulerJS");
		em.persist(student2);
		
		em.flush();
		
		// em.clear(); --We can also use clear() instead of detach()
		//em.detach(student1);
		//em.detach(student2);
		
		student2.setName("Learn AngulerJS - Updated");
		
		student1.setName("Learn CI/CD-updated");

		//em.refresh(student1);
		
		//em.flush();
		
		Student student2 = findById(10002L);
		student2.setName("JPA in 100 steps- updated");
		}
	 */
		
	// Save() after relationship
	public void saveStudentWithPassport(){
		Passport passport = new Passport("Z14454");
		em.persist(passport);
		
		Student student = new Student("Mike");
		student.setPassport(passport);
		
		em.persist(student);
	}
	
	//To understand the persistance context
	public void someDummyOperation() {
		//DB op1 - retrieve Student 
		Student student = em.find(Student.class, 20001L);
		//Persistance Context(student)
		
		//DB op2 - retrieve Passport
		Passport passport = student.getPassport();
		//Persistance Context(student,passport)
		
		//DB op3 - update passport
		passport.setNumber("E139jf");
		//Persistance Context(student,passport++)
		
		//DB op4 - update Student
		student.setName("Abhishek-upated");
		//Persistance Context(student++,passport++)
	}
	
	public void insertStudentAndCourseHardCoded() {
		Student student = new Student("Jack");
		Course course = new Course("Micro-service");
		
		em.persist(student);
		em.persist(course);
		
		student.addCourses(course);
		course.addStudents(student);
		
		em.persist(student);
	}
	
	public void insertStudentAndCourse(Student student , Course course) {
		//Student student = new Student("Jack");
		//Course course = new Course("Micro-service");
		student.addCourses(course);
		course.addStudents(student);
		
		em.persist(student);
		em.persist(course);
		
	}
	
}
