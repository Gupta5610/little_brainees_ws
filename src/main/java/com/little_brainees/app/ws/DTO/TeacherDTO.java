package com.little_brainees.app.ws.DTO;

import java.io.Serializable;


import javax.xml.bind.annotation.*;;


@XmlRootElement 
public class TeacherDTO extends BaseDTO implements Serializable{
	
	private static final long  serialVersionUID = 1L;
	String teacherID;
	String teacherName;
	String teacherAddress;
	String teacherEmail;
	String teacherPhoneNumber;
	String teacherBio;
	String teacherAdharNumber;
	String teacherClass;
	
	public TeacherDTO(){
		
	}
	
	
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherAddress() {
		return teacherAddress;
	}
	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}
	public String getTeacherEmail() {
		return teacherEmail;
	}
	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
	public String getTeacherPhoneNumber() {
		return teacherPhoneNumber;
	}
	public void setTeacherPhoneNumber(String teacherPhoneNumber) {
		this.teacherPhoneNumber = teacherPhoneNumber;
	}
	public String getTeacherBio() {
		return teacherBio;
	}
	public void setTeacherBio(String teacherBio) {
		this.teacherBio = teacherBio;
	}
	public String getTeacherAdharNumber() {
		return teacherAdharNumber;
	}
	public void setTeacherAdharNumber(String teacherAdharNumber) {
		this.teacherAdharNumber = teacherAdharNumber;
	}
	public String getTeacherClass() {
		return teacherClass;
	}
	public void setTeacherClass(String teacherClass) {
		this.teacherClass = teacherClass;
	}	
	
	
	public String toJSON() {
		String returnValue = "{"
				+ "\"teacherName\" :\""+  this.teacherName  +"\""
				+ "}";
		
		return returnValue;
		
	}
}
