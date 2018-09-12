package com.little_brainees.app.ws.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.little_brainees.app.ws.request.ChildRequestObject;;

@XmlRootElement 
public class CreateTeacherResponse {
	
	Boolean isActive;
	String teacherID;
	String teacherName;
	String teacherAddress;
	String teacherEmail;
	String teacherPhoneNumber;
	String teacherBio;
	String teacherAdharNumber;
	String teacherClass;
	List<ChildRequestObject> children;
	
	
	/**
	 * @return the teacherAddress
	 */
	public String getTeacherAddress() {
		return teacherAddress;
	}
	/**
	 * @return the teacherBio
	 */
	public String getTeacherBio() {
		return teacherBio;
	}
	/**
	 * @return the teacherAdharNumber
	 */
	public String getTeacherAdharNumber() {
		return teacherAdharNumber;
	}
	/**
	 * @return the teacherClass
	 */
	public String getTeacherClass() {
		return teacherClass;
	}
	/**
	 * @return the children
	 */
	public List<ChildRequestObject> getChildren() {
		return children;
	}
	/**
	 * @param teacherAddress the teacherAddress to set
	 */
	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}
	/**
	 * @param teacherBio the teacherBio to set
	 */
	public void setTeacherBio(String teacherBio) {
		this.teacherBio = teacherBio;
	}
	/**
	 * @param teacherAdharNumber the teacherAdharNumber to set
	 */
	public void setTeacherAdharNumber(String teacherAdharNumber) {
		this.teacherAdharNumber = teacherAdharNumber;
	}
	/**
	 * @param teacherClass the teacherClass to set
	 */
	public void setTeacherClass(String teacherClass) {
		this.teacherClass = teacherClass;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ChildRequestObject> children) {
		this.children = children;
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
}
