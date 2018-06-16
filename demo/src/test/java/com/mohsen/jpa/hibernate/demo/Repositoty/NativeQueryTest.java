package com.mohsen.jpa.hibernate.demo.Repositoty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

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
public class NativeQueryTest {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;

	@Test
	public void native_queries_basic() {
		Query query= em.createNativeQuery("SELECT * FROM course",Course.class);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM course --->{}",resultList);
	}
	
	@Test
	public void native_queries_filtered_basic() {
		Query query= em.createNativeQuery("SELECT * FROM course where id = ?",Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM course --->{}",resultList);
	}
	
	@Test
	public void native_queries_namedParameter_basic() {
		Query query= em.createNativeQuery("SELECT * FROM course where id = :id",Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM course --->{}",resultList);
	}
	
	@Test
	@Transactional
	public void native_queries_update() {
		Query query= em.createNativeQuery("update course set last_updated_date=sysdate()",Course.class);
		int numOfRows = query.executeUpdate();
		logger.info("numOfRows --->{}",numOfRows);
	}
}
