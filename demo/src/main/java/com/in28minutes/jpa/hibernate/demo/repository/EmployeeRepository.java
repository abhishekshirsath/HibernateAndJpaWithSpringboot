package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Employee;
import com.in28minutes.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.PartTimeEmployee;
import com.in28minutes.jpa.hibernate.demo.entity.Review;

import jakarta.persistence.EntityManager;

@Repository
@Transactional
public class EmployeeRepository {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired	
	EntityManager em;
	
	// Insert Employee
	public void insert(Employee employee) {
		em.persist(employee);
	}
	
	// Update an Employee
	/* 
	 * public List<Employee> retrieveAllEmployee(){ return
	 * em.createQuery("Select e from Employee e",Employee.class).getResultList(); }
	 */
	
	public List<PartTimeEmployee> retrieveAllPartTimeEmployee(){
		return em.createQuery("Select e from PartTimeEmployee e",PartTimeEmployee.class).getResultList();
	}
	
	public List<FullTimeEmployee> retrieveAllFullTimeEmployee(){
		return em.createQuery("Select e from FullTimeEmployee e",FullTimeEmployee.class).getResultList();
	}
	


}
