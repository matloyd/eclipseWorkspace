package com.mohsen.jpa.hibernate.demo.Repositoty;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mohsen.jpa.hibernate.demo.entity.Passport;
import com.mohsen.jpa.hibernate.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@Autowired
	EntityManager em;
	private Logger logger=LoggerFactory.getLogger(this.getClass());

	public Student findById(Long id) {
		return em.find(Student.class,id);
	}
	
	public Student save(Student student) {
		if(student.getId()==null)
			em.persist(student);
		else
			em.merge(student);
		return student;
	}
	
	public void deleteById(Long id) {
		em.remove(findById(id));
	}
	
	public void saveStudentWithPassport() {
		Passport p1=new Passport("Q74482");
		em.persist(p1);
		Student s1=new Student("Mike");
		s1.setPassport(p1);
		em.persist(s1);
		
	}

}
