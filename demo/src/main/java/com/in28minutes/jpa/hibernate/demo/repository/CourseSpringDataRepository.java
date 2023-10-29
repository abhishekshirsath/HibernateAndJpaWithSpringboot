package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in28minutes.jpa.hibernate.demo.entity.Course;


@RepositoryRestResource(path = "courses")
//@JsonIgnone
public interface CourseSpringDataRepository extends JpaRepository<Course,Long> {
	
	List<Course> findByNameAndId(String name,Long id);
	List<Course> findByName(String name);
	List<Course> countByName(String name);
	List<Course> findByNameOrderById(String name);
	List<Course> deleteByName(String name);
	
	

}
