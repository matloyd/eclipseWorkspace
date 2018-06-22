package com.mohsen.jpa.hibernate.demo.Repositoty;

import javax.persistence.EntityManager;

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
	public void retrieveStudentWithPassport() {

		System.out.println(findById(20002L)+"\t"+findById(20002L).getPassport());
	}
}
