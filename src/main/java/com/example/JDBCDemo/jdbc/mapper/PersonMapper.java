package com.example.JDBCDemo.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.JDBCDemo.entity.Person;

public class PersonMapper implements RowMapper<Person> {

    private static final String NAME = "NAME";
    private static final String LOCATION = "LOCATION";
    private static final String BIRTHDATE = "BIRTH_DATE";

    @Override
    public Person mapRow(final ResultSet rs, final int rowNum) throws SQLException {

	Person person = new Person();

	person.setId(rs.getInt("ID"));
	person.setName(rs.getString(NAME));
	person.setLocation(rs.getString(LOCATION));
	person.setBirthDate(rs.getTimestamp(BIRTHDATE).toLocalDateTime());
	
	return person;
    }

}
