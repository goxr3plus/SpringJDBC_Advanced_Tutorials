package com.example.JDBCDemo;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.JDBCDemo.entity.Person;
import com.example.JDBCDemo.jdbc.PersonJDBCDao;
import com.example.JDBCDemo.jdbc.PersonJDBCDaoWithMapper;
import com.example.JDBCDemo.jdbc.advanced.PersonJDBCRepository;

@SpringBootApplication
public class JdbcDemoApplication implements CommandLineRunner {

    @Autowired
    private PersonJDBCDao personJDBCDao;

    @Autowired
    private PersonJDBCDaoWithMapper personJDBCDaoWithMapper;

    @Autowired
    private PersonJDBCRepository personJDBCRepository;

    public static void main(final String[] args) {
	SpringApplication.run(JdbcDemoApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
	// testJDBCDao()
	testJDBCDao_WithMapper();
	testJDBCRepository();
    }

    private void testJDBCDao() {

	/* Find Person by ID */
	System.err.println(personJDBCDao.findById(1001));

	/* Delete Person by ID */
	System.err.println(personJDBCDao.deleteById(1002));

	/* Find all Persons */
	System.err.println(personJDBCDao.findAll());

	/* Insert a new Person */
	Person person = new Person(50, "LAPLACE", "AFRICA", LocalDateTime.now());
	System.out.println(personJDBCDao.insert(new Person(50, "LAPLACE", "AFRICA", LocalDateTime.now())));

	/* Update Person */
	person.setLocation("Nevada");
	System.out.println(personJDBCDao.update(person));

    }

    private void testJDBCDao_WithMapper() {

	/* Find Person by ID */
	System.err.println(personJDBCDaoWithMapper.findById(1001));

	/* Delete Person by ID */
	System.err.println(personJDBCDaoWithMapper.deleteById(1002));

	/* Find all Persons */
	System.err.println(personJDBCDaoWithMapper.findAll());

	/* Insert a new Person */
	Person person = new Person(50, "LAPLACE", "AFRICA", LocalDateTime.now());
	System.out.println(personJDBCDaoWithMapper.insert(new Person(50, "LAPLACE", "AFRICA", LocalDateTime.now())));

	/* Update Person */
	person.setLocation("Nevada");
	System.out.println(personJDBCDaoWithMapper.update(person));

    }

    private void testJDBC_Repository() {

    }

}
