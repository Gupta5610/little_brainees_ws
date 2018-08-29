/**
 * 
 */
package com.little_brainees.app.ws.exceptions;

/**
 * @author ashish
 *
 */
public class MissingRequiredFieldException extends RuntimeException {
	private static final long serialVersionUID = 2L;
	public MissingRequiredFieldException(String errorMessage) {
		super(errorMessage);
	}
}
