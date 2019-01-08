package com.example.JDBCDemo.jdbc.advanced;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.util.CollectionUtils;

import com.example.JDBCDemo.enumaration.EntityOperation;
import com.example.JDBCDemo.exception.DemoException;

public abstract class AbstractJdbcRepository {

    private final Properties SQL_COMMANDS = new Properties();

    @Autowired
    private NamedParameterJdbcTemplate template;

    @PostConstruct
    private void setSqlCommands() throws Exception {

	final InputStream sqlInputStream = new ClassPathResource("/sql/sql.properties").getInputStream();

	SQL_COMMANDS.load(sqlInputStream);
	if (SQL_COMMANDS.isEmpty()) {
	    throw new Exception("SQL commands file empty");
	}
    }

    private String getSqlCommands(final String key) {

	if (SQL_COMMANDS.containsKey(key)) {
	    return SQL_COMMANDS.getProperty(key);
	}

	return null;
    }

    <T> T getOne(final String sqlQueryKey, final MapSqlParameterSource parameters, final Class<T> type) {

	try {
	    return template.queryForObject(this.getSqlCommands(sqlQueryKey), parameters, type);
	} catch (final EmptyResultDataAccessException e) {

	    throw new DemoException(new Object[] { sqlQueryKey, parameters, e.getMessage() });
	} catch (final DataAccessException e) {
	    throw new DemoException(new Object[] { sqlQueryKey, parameters, e.getMessage() });
	}
    }

    <T> T getOne(final String sqlQueryKey, final MapSqlParameterSource parameters, final RowMapper<T> rowMapper) {

	try {
	    return template.queryForObject(this.getSqlCommands(sqlQueryKey), parameters, rowMapper);
	} catch (final EmptyResultDataAccessException e) {
	    throw new DemoException(new Object[] { sqlQueryKey, parameters, e.getMessage() });
	} catch (final DataAccessException e) {
	    throw new DemoException(new Object[] { sqlQueryKey, parameters, e.getMessage() });
	}
    }

    <T> List<T> getList(final String sqlQueryKey, final MapSqlParameterSource parameters, final Class<T> type) {

	final List<T> result;
	try {
	    result = template.queryForList(this.getSqlCommands(sqlQueryKey), parameters, type);
	} catch (final DataAccessException e) {
	    throw new DemoException(new Object[] { sqlQueryKey, parameters, e.getMessage() });
	}
	return result;
    }

    protected <T> List<T> getList(final String sqlQueryKey, final MapSqlParameterSource parameters, final RowMapper<T> rowMapper) {

	final List<T> result;
	try {
	    result = template.query(this.getSqlCommands(sqlQueryKey), parameters, rowMapper);
	} catch (final DataAccessException e) {
	    throw new DemoException(new Object[] { sqlQueryKey, parameters, e.getMessage() });
	}
	return result;
    }

    <T> Long update(final String sqlQueryKey, final MapSqlParameterSource parameters, final Integer expectedRowsToUpdate, final EntityOperation entityOperation) {

	final Integer rowsAffected;
	final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	try {
	    rowsAffected = template.update(this.getSqlCommands(sqlQueryKey), parameters);
	    if (expectedRowsToUpdate != null && rowsAffected != expectedRowsToUpdate) {
		throw new DemoException(new Object[] { sqlQueryKey, expectedRowsToUpdate, rowsAffected });
	    }
	} catch (final EmptyResultDataAccessException e) {
	    throw new DemoException(new Object[] { sqlQueryKey, parameters, e.getMessage() });
	} catch (final DataAccessException e) {
	    final String message = MessageFormat.format("A general error occurred while executing SQL query={0}, parameters={1}. message={2}", sqlQueryKey, parameters, e.getMessage());
	    throw new DemoException(new Object[] { sqlQueryKey, parameters, e.getMessage() });
	}

	/* SCOPE_IDENTITY is populated only on test environment using H2 database */
	if (EntityOperation.CREATE.equals(entityOperation) && !CollectionUtils.isEmpty(keyHolder.getKeys())) {
	    if (keyHolder.getKey() != null) {
		return keyHolder.getKey().longValue();
	    } else {
		return null;
	    }
	} else {
	    return rowsAffected.longValue();
	}
    }

}
