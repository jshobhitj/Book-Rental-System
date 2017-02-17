package com.impetus.exceptions;

public class DAOException extends RuntimeException {


	/**
	 * Custom DAO Layer Exception Class.
	 */
	private static final long serialVersionUID = -1499054619512741466L;

	public DAOException() {
		super();
	}

	public DAOException(String str, Throwable thr) {
		super(str, thr);
	}

	public DAOException(String str) {
		super(str);
	}

	public DAOException(Throwable thr) {
		super(thr);
	}
}