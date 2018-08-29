package com.little_brainees.app.ws.request;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class CreateClassRequest {
	
	private String classCode ;
	private String className ;
	
	
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
}
