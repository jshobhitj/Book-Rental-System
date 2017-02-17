package com.impetus.exceptions;

public class ServiceLayerException extends RuntimeException {

	
	/**
	 * Custom Service Layer Exception Class.
	 */
	private static final long serialVersionUID = -6903385560700947984L;

	public ServiceLayerException() {
		super();
	}

	public ServiceLayerException(String str, Throwable thr) {
		super(str, thr);
	}

	public ServiceLayerException(String str) {
		super(str);
	}

	public ServiceLayerException(Throwable thr) {
		super(thr);
	}
}