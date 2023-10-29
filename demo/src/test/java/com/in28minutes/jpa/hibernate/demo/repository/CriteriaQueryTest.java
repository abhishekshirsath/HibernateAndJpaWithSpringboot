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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void all_course() {
		//"Select c from Course c"
		
		//1. Use criteria builder(for where , from) to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2 Define roots for tables which are involed in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3 Define predicates etc using Criteria Builder
		
		
		//4 Add Predicates etc to Crieteria query
		
		
		//5 Build the TypeQuery using the entity manager and criteria query
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		
		logger.info("Type Query:(Criteria Qyuery):: {}",resultList);
		//Type Query:(Criteria Qyuery):: [Course [name=JPA in 50 steps], Course [name=Spring boot in 50 steps], Course [name=Jenkins in 50 steps]]
		
	}
	
	@Test
	public void all_course_having_50steps() {
		//"Select c from Course c where name like '%50 Steps' "
		
		//1. Use criteria builder(for where , from) to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2 Define roots for tables which are involed in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3 Define predicates etc using Criteria Builder
		Predicate like50Steps = cb.like(courseRoot.get("name"), "%50 steps");
		
		//4 Add Predicates etc to Crieteria query
		cq.where(like50Steps);
		
		//5 Build the TypeQuery using the entity manager and criteria query
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		
		logger.info("Type Query:(Criteria Qyuery):: {}",resultList);
		
		//Type Query:(Criteria Qyuery):: [Course [name=JPA in 50 steps], Course [name=Spring boot in 50 steps], Course [name=Jenkins in 50 steps]]
			
	}
	
	@Test
	public void all_course_without_students() {
		//"Select c from Course c where c.students is empty"
		
		//1. Use criteria builder(for where , from) to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2 Define roots for tables which are involed in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3 Define predicates etc using Criteria Builder
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));
		
		//4 Add Predicates etc to Crieteria query
		cq.where(studentsIsEmpty);
		
		//5 Build the TypeQuery using the entity manager and criteria query
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		
		logger.info("Type Query:(Criteria Qyuery):: {}",resultList);
	
		//	Type Query:(Criteria Qyuery):: [Course [name=Spring boot in 50 steps]]
	}
	
	@Test
	public void join() {
		//"Select c from Course c JOIN c.studenents s"
		
		//1. Use criteria builder(for where , from) to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2 Define roots for tables which are involed in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3 Define predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students");
		
		//4 Add Predicates etc to Crieteria query
		
		//5 Build the TypeQuery using the entity manager and criteria query
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		
		logger.info("Type Query:(Criteria Qyuery):: {}",resultList);
	
		//	Type Query:(Criteria Qyuery):: [Course [name=JPA in 50 steps], Course [name=Jenkins in 50 steps]]
	}
	
	@Test
	public void left_join() {
		//"Select c from Course c LEFT JOIN c.studenents s"
		
		//1. Use criteria builder(for where , from) to create a Criteria Query returning the expected result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2 Define roots for tables which are involed in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3 Define predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students",JoinType.LEFT);
		
		//4 Add Predicates etc to Crieteria query
		
		//5 Build the TypeQuery using the entity manager and criteria query
		
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList = query.getResultList();
		
		logger.info("Type Query:(Criteria Qyuery):: {}",resultList);
	
		//	Type Query:(Criteria Qyuery):: [Course [name=JPA in 50 steps], Course [name=Spring boot in 50 steps], Course [name=Jenkins in 50 steps]]
	}
	
	
	

}
