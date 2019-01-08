package com.example.JDBCDemo.entity;

import java.time.LocalDateTime;

/**
 * @author akentros
 *
 */
public class Person {

	private int id;
	private String name;
	private String location;
	private LocalDateTime birthDate;

	public Person() {
		super();
	}

	public Person(final int id, final String name, final String location, final LocalDateTime birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(final LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "\nPerson [id=" + id + ", name=" + name + ", location=" + location + ", birthDate=" + birthDate + "]";
	}

}
