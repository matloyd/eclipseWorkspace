package com.mohsen.jpa.hibernate.demo.Repositoty;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mohsen.jpa.hibernate.demo.entity.Course;

@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em;
	private Logger logger=LoggerFactory.getLogger(this.getClass());

	public Course findById(Long id) {
		return em.find(Course.class,id);
	}
	
	public Course save(Course course) {
		if(course.getId()==null)
			em.persist(course);
		else
			em.merge(course);
		return course;
	}
	
	public void deleteById(Long id) {
		em.remove(findById(id));
	}
	
	public void playWithEntityManager() {
		Course c1=new Course("Jafar be jaam raft");
		em.persist(c1);
		
		Course c2=findById(10001L);
		c2.setName("JPA in 100 steps! - UPDATED");
		em.persist(c2);
		
	}

}
