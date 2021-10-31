package ar.com.wenace.wenaceChallenge.exception;

public class BadRequestException extends Exception {

	private static final long serialVersionUID = -1792295689026916452L;

	public BadRequestException(String message) {
		super(message);
	}
}
