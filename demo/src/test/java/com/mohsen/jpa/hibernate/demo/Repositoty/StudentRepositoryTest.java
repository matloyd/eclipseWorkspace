package com.mohsen.jpa.hibernate.demo.Repositoty;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mohsen.jpa.hibernate.demo.DemoApplication;
import com.mohsen.jpa.hibernate.demo.entity.Passport;
import com.mohsen.jpa.hibernate.demo.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class StudentRepositoryTest {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository sr;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		 Student student = sr.findById(20001L);
		 logger.info("student ---> {}",student);
		 logger.info("passport ---> {}",student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		 Passport p1 = em.find(Passport.class,40002L);
		 logger.info("passport ---> {}",p1);
		 logger.info("student ---> {}",p1.getStudent());
	}

}
