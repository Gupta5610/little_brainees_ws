/**
 * 
 */
package com.little_brainees.app.ws.exceptions;

/**
 * @author ashish
 *
 */
public enum ErrorMessages {
	
	MISSING_REQUIRED_FIELD("Missing required field.Please check documention for required Fields"),
	DUPLICATE_RECORD_FOUND("Duplicate Record Found.please check documentation for Duplicate records"),
	RECORD_NOT_FOUND("Record Not Found.please check documentation for Missing Records");
	
	
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	ErrorMessages(String errorMessage){
		this.errorMessage = errorMessage ;
	}

}
