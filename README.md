# SpringJDBCTutorials
On this repository you can find both simple and advanced code on how to use Spring JDBC Templates in many ways .

# Details

This repository contains code for the following :

1) Using JDBC Template for all basic CRUD operations [(no custom mappers)](https://github.com/goxr3plus/SpringJDBC_Advanced_Tutorials/blob/master/src/main/java/com/example/JDBCDemo/jdbc/PersonJDBCDao.java)
2) Using JDBC Template for all basic CRUD operations [**(with custom mappers)**](https://github.com/goxr3plus/SpringJDBC_Advanced_Tutorials/blob/master/src/main/java/com/example/JDBCDemo/jdbc/PersonJDBCDaoWithMapper.java)
3) Using JDBC Template for all basic CRUD operations 
   [**loading sql commands from a *sql.properties* file using a custom class**](https://github.com/goxr3plus/SpringJDBC_Advanced_Tutorials/blob/master/src/main/java/com/example/JDBCDemo/jdbc/advanced/PersonJDBCRepository.java)
   
This repository was based on [hibernate-jpa-tutorial-for-beginners-in-100-steps](https://www.udemy.com/hibernate-jpa-tutorial-for-beginners-in-100-steps/) and my personal working experience :)


Example for bullet (1) : 
Using JDBC Template for all basic CRUD operations [(no custom mappers)](https://github.com/goxr3plus/SpringJDBC_Advanced_Tutorials/blob/master/src/main/java/com/example/JDBCDemo/jdbc/PersonJDBCDao.java)


``` JAVA
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.JDBCDemo.entity.Person;

@Repository
public class PersonJDBCDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Person> findAll() {
		return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
	}

	public Person findById(final int id) {

		return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id=?", new Object[] { id },
				new BeanPropertyRowMapper<>(Person.class));

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

```

Example for bullet (3):
Using JDBC Template for all basic CRUD operations 
   [**loading sql commands from a *sql.properties* file using a custom class**](https://github.com/goxr3plus/SpringJDBC_Advanced_Tutorials/blob/master/src/main/java/com/example/JDBCDemo/jdbc/advanced/PersonJDBCRepository.java)

``` JAVA
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
```
