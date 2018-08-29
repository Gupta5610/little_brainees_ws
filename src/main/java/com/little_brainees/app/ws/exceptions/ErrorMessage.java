/**
 * 
 */
package com.little_brainees.app.ws.exceptions;

import javax.xml.bind.annotation.*;

/**
 * @author ashish
 *
 */

@XmlRootElement
public class ErrorMessage {
	
	private String errorMessage;
	private int errorCode;
	private String href;
	
	
	public ErrorMessage(){
		
	}
	
	public ErrorMessage(String errorMessage,int errorCode , String href) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.href = href;
	}
	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorMessageKey) {
		this.errorCode = errorMessageKey;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
}
