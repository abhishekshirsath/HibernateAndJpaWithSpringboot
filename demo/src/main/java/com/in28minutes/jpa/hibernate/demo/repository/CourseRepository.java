package com.in28minutes.jpa.hibernate.demo.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import com.in28minutes.jpa.hibernate.demo.entity.ReviewRating;

import jakarta.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired	
	EntityManager em;

	//findById
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	//Save  = insert + update
	public Course save(Course course) {
		if(course.getId() == null) {
			// insert data
			em.persist(course);
		}else {
			// Update data
			em.merge(course);
		}
		return course;

	}

	//deleteById
	public void deleteById(Long id) {

		Course course =	findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		logger.info("PlayWithEntityManager - Start");

		Course course1 = new Course("Learn CI/CD");
		em.persist(course1);
		em.flush();

		/*
		Course course2 = new Course("Learn AngulerJS");
		em.persist(course2);

		em.flush();

		// em.clear(); --We can also use clear() instead of detach()
		//em.detach(course1);
		//em.detach(course2);

		course2.setName("Learn AngulerJS - Updated");
		 */
		course1.setName("Learn CI/CD-updated");

		//em.refresh(course1);

		//em.flush();

		Course course2 = findById(10002L);
		course2.setName("JPA in 100 steps- updated");
	}

	public void addHardCodedReviewsForCourse() {
		// TODO Auto-generated method stub
		// get course 1003

		Course course = findById(10001L);
		logger.info("course.getReview() --> {}", course.getReviews());

		// add 2 review 
		Review review1 = new Review(ReviewRating.FIVE,"Great Hands On Studff");
		Review review2 = new Review(ReviewRating.FOUR,"Hats off");

		course.addReviews(review1);
		review1.setCourse(course);

		course.addReviews(review2);
		review2.setCourse(course);

		em.persist(review1);
		em.persist(review2);

		// save it to db



	}

	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		// TODO Auto-generated method stub
		// get course 1003

		Course course = findById(courseId);
		logger.info("course.getReview() --> {}", course.getReviews());

		// add 2 review 
		for(Review review:reviews) {

			course.addReviews(review);
			review.setCourse(course);


			em.persist(review);

			// save it to db

		}

	}
}
