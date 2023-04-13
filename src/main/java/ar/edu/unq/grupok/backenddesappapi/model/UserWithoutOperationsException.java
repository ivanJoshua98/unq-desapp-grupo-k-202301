package ar.edu.unq.grupok.backenddesappapi.model;

public class UserWithoutOperationsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserWithoutOperationsException(String message) {
        super(message);
    }

}
