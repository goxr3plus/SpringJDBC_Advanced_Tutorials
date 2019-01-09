package com.example.JDBCDemo.jdbc.advanced;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.example.JDBCDemo.entity.Person;
import com.example.JDBCDemo.enumaration.EntityOperation;
import com.example.JDBCDemo.jdbc.mapper.PersonMapper;

@Component
public class PersonJDBCRepository extends AbstractJdbcRepository {

    public List<Person> findAll() {

	final MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();

	return this.getList("SELECT_ALL_PERSONS", sqlParameterSource, new PersonMapper());
    }

    public Person findById(final int id) {

	final MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
	sqlParameterSource.addValue("id", id);

	return this.getOne("SELECT_BY_ID", sqlParameterSource, new PersonMapper());
    }

    public Long deleteById(final int id) {

	final MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
	sqlParameterSource.addValue("id", id);

	return this.update("DELETE_BY_ID", sqlParameterSource, 1, EntityOperation.DELETE);
    }

    public Long insert(final Person person) {

	final MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
	sqlParameterSource.addValue("id", person.getId());
	sqlParameterSource.addValue("name", person.getName());
	sqlParameterSource.addValue("location", person.getLocation());
	sqlParameterSource.addValue("birth_date", person.getBirthDate());

	return this.update("INSERT_PERSON", sqlParameterSource, 1, EntityOperation.CREATE);
    }
    
    public Long update(final Person person) {

	final MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
	sqlParameterSource.addValue("id", person.getId());
	sqlParameterSource.addValue("name", person.getName());
	sqlParameterSource.addValue("location", person.getLocation());
	sqlParameterSource.addValue("birthday", person.getBirthDate());

	return this.update("UPDATE_PERSON", sqlParameterSource, 1, EntityOperation.UPDATE);
    }

}
