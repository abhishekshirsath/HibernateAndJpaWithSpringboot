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

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	@Test
	public void native_queries_basic() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where is_deleted = 0",Course.class);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE {}",resultList);
		// SELECT * FROM COURSE [Course [name=Learn CI/CD-updated], Course [name=JPA in 50 steps], Course [name=JPA in 100 steps- updated], Course [name=Jenkins in 50 steps]]
	}
	
	@Test
	public void native_queries_with_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = ?",Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE where id = ? {}",resultList);
		// SELECT * FROM COURSE where id = ? [Course [name=JPA in 50 steps]]
	}
	
	@Test
	public void native_queries_with_named_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id",Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE where id = ? {}",resultList);
		// SELECT * FROM COURSE where id = ? [Course [name=JPA in 50 steps]]
	}
	
	/*
	@Test
	@Transactional
	public void native_queries_to_update() {
		Query query = em.createNativeQuery("update Course set name = 'ABHISHEK' ",Course.class);
		int noOfUpdated = query.executeUpdate();
		logger.info("SELECT * FROM COURSE {}",noOfUpdated);
		// SELECT * FROM COURSE [Course [name=Learn CI/CD-updated], Course [name=JPA in 50 steps], Course [name=JPA in 100 steps- updated], Course [name=Jenkins in 50 steps]]
	}
	
	*/
	
}
