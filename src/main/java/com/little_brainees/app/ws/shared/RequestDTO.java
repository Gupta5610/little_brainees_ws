/**
 * 
 */
package com.little_brainees.app.ws.shared;

/**
 * @author ashish
 *
 */
public class RequestDTO {
	
	private String requestString;
	private Class type;
	

	public RequestDTO(String requestString, Class type) {
		super();
		this.requestString = requestString;
		this.type = type;
	}
	/**
	 * @return the requestString
	 */
	public String getRequestString() {
		return requestString;
	}
	/**
	 * @param requestString the requestString to set
	 */
	public void setRequestString(String requestString) {
		this.requestString = requestString;
	}
	/**
	 * @return the type
	 */
	public Class getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Class type) {
		this.type = type;
	}

}
