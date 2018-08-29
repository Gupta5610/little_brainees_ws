/**
 * 
 */
package com.little_brainees.app.ws.exceptions;

import java.io.Serializable;

/**
 * @author ashish
 *
 */
public class RecordNotFoundException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4548123038130787391L;
	
	public RecordNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
