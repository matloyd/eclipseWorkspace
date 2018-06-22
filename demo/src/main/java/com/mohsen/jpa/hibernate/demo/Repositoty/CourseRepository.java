package com.mohsen.jpa.hibernate.demo.Repositoty;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mohsen.jpa.hibernate.demo.entity.Course;
import com.mohsen.jpa.hibernate.demo.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {
		if (course.getId() == null)
			em.persist(course);
		else
			em.merge(course);
		return course;
	}

	public void deleteById(Long id) {
		em.remove(findById(id));
	}

	public void playWithEntityManager() {
		Course c1 = new Course("Jafar be jaam raft");
		em.persist(c1);

		Course c2 = findById(10001L);
		c2.setName("JPA in 100 steps! - UPDATED");
		em.persist(c2);
	}

	public void addReviewForCourse() {
		Course c = findById(10003L);
		logger.info("course.getReviews() -->{}", c.getReviews());

		Review review1 = new Review("4", "Fantabulous");
		Review review2 = new Review("0", "Disastrous!");

		c.addReview(review1);
		review1.setCourse(c);

		c.addReview(review2);
		review2.setCourse(c);

		em.persist(review1);
		em.persist(review2);

		em.flush();

		logger.info("course.getReviews() -->{}", c.getReviews());
	}

	public void addReviewForCourse(Long courseID, List<Review> reviews) {
		Course c = findById(courseID);
		logger.info("course.getReviews() -->{}", c.getReviews());

		for (Review review : reviews) {
			c.addReview(review);
			review.setCourse(c);
			em.persist(review);
		}
		logger.info("course.getReviews() -->{}", c.getReviews());
	}

}
