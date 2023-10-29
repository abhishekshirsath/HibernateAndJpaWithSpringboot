package com.in28minutes.jpa.hibernate.demo.repository;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import jakarta.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class PerformanceTuningTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	public void creatingNPlusProblem() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses",Course.class).getResultList();
		
		for(Course course:courses) {
			logger.info("Course -> {} Students-> {}",course,course.getStudents());
		}
		
		
	}
	
	@Test
	@Transactional
	public void solvingNPlusProblem() {
		
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> subSubgraph = entityGraph.addSubgraph("students");
		
		List<Course> courses = em
				.createNamedQuery("query_get_all_courses",Course.class)
				.setHint("javax.persistence.loadgraph;", entityGraph)
				.getResultList();
		
		for(Course course:courses) {
			logger.info("Course -> {} Students-> {}",course,course.getStudents());
		}
	}
	
	@Test
	@Transactional
	public void solvingNPlusProblem_JoinFetch() {
		List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch",Course.class).getResultList();
		
		for(Course course:courses) {
			logger.info("Course -> {} Students-> {}",course,course.getStudents());
		}
		
		
	}

}
