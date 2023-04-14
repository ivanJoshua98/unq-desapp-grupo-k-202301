package ar.edu.unq.grupok.backenddesappapi.model;

public class EmailAlreadyUsedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyUsedException(String message) {
		super(message);
	}
}
