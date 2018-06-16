package com.mohsen.jpa.hibernate.demo.Repositoty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mohsen.jpa.hibernate.demo.DemoApplication;
import com.mohsen.jpa.hibernate.demo.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class JPQLTest {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;

	@Test
	public void rawQuery() {
		List resultList = em.createNamedQuery("queri_all_courses").getResultList();
		logger.info("SELECT c FROM course c --->{}",resultList);
	}
	
	@Test
	public void typedQuery() {
		TypedQuery<Course> query = em.createNamedQuery("queri_all_courses",Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT c FROM course c --->{}",resultList);
	}
	
	@Test
	public void jpql_where() {
		TypedQuery<Course> query = em.createNamedQuery("queri_100Steps_courses",Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("SELECT c FROM course c --->{}",resultList);
	}
}
