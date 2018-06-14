package com.mohsen.jpa.hibernate.demo.Repositoty;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mohsen.jpa.hibernate.demo.DemoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoApplication.class)
public class CourseRepositoryTest {
	
	@Autowired
	CourseRepository cr;

	@Test
	public void findById_basics() {
		assertEquals("JPA in 100 steps!", cr.findById(10001L).getName());
	}

}
