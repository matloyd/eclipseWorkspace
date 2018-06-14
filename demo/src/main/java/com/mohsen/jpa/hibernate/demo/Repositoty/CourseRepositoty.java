package com.mohsen.jpa.hibernate.demo.Repositoty;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mohsen.jpa.hibernate.demo.entity.Course;

@Repository
public class CourseRepositoty {
	
	@Autowired
	EntityManager em;
	
	public Course findById(Long id) {
		return em.find(Course.class,id);
	}

}
