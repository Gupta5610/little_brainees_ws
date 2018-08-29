/**
 * 
 */
package com.little_brainees.app.ws.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ashish
 *
 */

@XmlRootElement
public class CreateClassResponse {
	public String classCode ;
	
	
	
	public CreateClassResponse() {
		
	}
	
    public CreateClassResponse(String classCode) {
		this.classCode = classCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classID) {
		this.classCode = classID;
	}
}
