package com.mohsen.jpa.hibernate.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mohsen.jpa.hibernate.demo.Repositoty.CourseRepositoty;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepositoty repository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Course 10001 -> {}",repository.findById(10001L));
	}
}
