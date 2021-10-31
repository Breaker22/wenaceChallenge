package ar.com.wenace.wenaceChallenge.exception;

public class ServiceFailedException extends Exception {

	private static final long serialVersionUID = 1281963143594577900L;
	
	public ServiceFailedException(String message) {
		super(message);
	}

}
