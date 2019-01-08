package com.example.JDBCDemo.jdbc.advanced;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.example.JDBCDemo.entity.Person;
import com.example.JDBCDemo.jdbc.mapper.PersonMapper;

@Component
public class PersonJDBCRepository extends AbstractJdbcRepository {

    public List<Person> getAllPersons() {

	final MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();

	return this.getList("SELECT_ALL_PERSONS", sqlParameterSource, new PersonMapper());
    }

}
