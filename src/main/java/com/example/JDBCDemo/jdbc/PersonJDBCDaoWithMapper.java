package com.example.JDBCDemo.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.JDBCDemo.entity.Person;
import com.example.JDBCDemo.jdbc.mapper.PersonMapper;

/**
 * IN THIS CLASS WE are using our custom defined mapper
 * 
 * @author GOXR3PLUS
 *
 */
@Repository
public class PersonJDBCDaoWithMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Person> findAll() {
	return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person findById(final int id) {

	return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=?", new Object[] { id }, new PersonMapper());

    }

    public int deleteById(final int id) {

	return jdbcTemplate.update("DELETE FROM person WHERE id=?", new Object[] { id });

    }

    public int insert(final Person person) {
	return jdbcTemplate.update("INSERT INTO person(id,name,location,birth_date) VALUES (?,?,?,?)",
		new Object[] { person.getId(), person.getName(), person.getLocation(), person.getBirthDate() });
    }

    public int update(final Person person) {
	return jdbcTemplate.update("UPDATE person " + "SET name= ? , location= ? , birth_date= ? " + "WHERE id= ? ",
		new Object[] { person.getName(), person.getLocation(), person.getBirthDate(), person.getId() });
    }

}
