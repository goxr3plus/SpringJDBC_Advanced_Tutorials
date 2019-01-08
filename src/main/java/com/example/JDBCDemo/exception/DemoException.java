package com.example.JDBCDemo.exception;

public class DemoException extends RuntimeException {

    private final Object[] object;

    public DemoException(final Object[] object) {
	super();
	this.object = object;
    }

    public Object[] getObject() {
	return object;
    }

}
