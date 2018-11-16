package com.oscarspring.tournament.rest.exception;

public class TournamentNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TournamentNotFoundException() {
		super();
	}

	public TournamentNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public TournamentNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public TournamentNotFoundException(String arg0) {
		super(arg0);
	}

	public TournamentNotFoundException(Throwable arg0) {
		super(arg0);
	}
	
	
	
}
