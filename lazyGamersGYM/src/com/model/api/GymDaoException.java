package com.model.api;

/**
 * the exception file to throw exception in the gym dao
 */
public class GymDaoException extends Exception {
	private static final long serialVersionUID = 1L;

	public GymDaoException(String msg) {
		super(msg);
	}

	public GymDaoException(String msg, Throwable rootcause) {
		super(msg, rootcause);
	}
}