package com.mohsen.jpa.hibernate.demo.Repositoty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.mohsen.jpa.hibernate.demo.DemoApplication;
import com.mohsen.jpa.hibernate.demo.entity.Course;
import com.mohsen.jpa.hibernate.demo.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class CourseRepositoryTest {
	
	@Autowired
	CourseRepository cr;
	
	@Autowired
	EntityManager em;
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());

	@Test
	public void findById_basics() {
		assertEquals("JPA in 100 steps!", cr.findById(10001L).getName());
	}
	
	@Test
	@DirtiesContext
	public void deleteById_basics() {
		cr.deleteById(10001L);
		assertNull(cr.findById(10001L));
	}
	
	@Test
	@DirtiesContext
	public void save_basics() {
		Course c = cr.findById(10001l);
		assertEquals("JPA in 100 steps!", c.getName());
		c.setName("JPA in 100 steps! - Updated");
		cr.save(c);
		assertEquals("JPA in 100 steps! - Updated", cr.findById(10001l).getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		cr.playWithEntityManager();
	}
	
	@Test
	public void retrieveReviewsForCourse() {
		logger.info("{}",cr.findById(10001L).getReviews());
	}
	
	@Test
	public void retrieveCourseForReview() {
		Review r = em.find(Review.class, 50001L);
		logger.info("{}",r.getCourse());
	}

}
