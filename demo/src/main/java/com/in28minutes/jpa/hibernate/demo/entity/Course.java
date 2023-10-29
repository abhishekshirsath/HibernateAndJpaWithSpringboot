package com.in28minutes.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
//@Table(name = "CourseDetails")
	
@NamedQueries(value = { 
		@NamedQuery(name="query_get_all_courses",query="select c from Course c"),
		@NamedQuery(name="query_get_all_courses_join_fetch",query="select c from Course c JOIN FETCH c.students s"),
		@NamedQuery(name = "query_get_50_step_course",query= "select c from Course c where name like '%50 steps' ")
				})
//@Cacheable
@SQLDelete(sql = "update course set is_deleted = true where id = ?")
@Where(clause = "is_deleted = false")
public class Course {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Course.class);

	@Id
	@GeneratedValue
	private Long id;
	
	//@Column(name = "fullname",nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList();

	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<>();
	
//	@UpdateTimestamp
//	private String lastUpdatedDate;
//	
//	@CreationTimestamp
//	private String createdDate;
	
	
	private boolean isDeleted;
	
	@PreRemove
	private void preRemove() {
		LOGGER.info("Setting is deleted True");
		this.isDeleted = true;
	}
	
	protected Course() {
		
	}
	
	public Course(String name) {
		super();
		this.name = name;
	}
	
	

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReviews(Review reviews) {
		this.reviews.add(reviews);
	}
	
	public void removeReviews(Review reviews) {
		this.reviews.remove(reviews);
	}
	
	

	public List<Student> getStudents() {
		return students;
	}

	public void addStudents(Student student) {
		this.students.add(student);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}
	
	
	
}
