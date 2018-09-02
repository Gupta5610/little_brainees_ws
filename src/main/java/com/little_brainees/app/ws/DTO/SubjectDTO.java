package com.little_brainees.app.ws.DTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SubjectDTO extends BaseDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String subjectCode;
	
	@Transient
	@Column(name = "ClassCode")
	String classCode;
	
	String subjectName;
	
	
	public SubjectDTO() {
		
	}
	
	public SubjectDTO(String subjectCode , String classCode , String subjectName) {
		this.subjectCode = subjectCode;
		this.classCode = classCode;
		this.subjectName = subjectName;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	@Override
	public String toString() {
		return "Subject [SubjectCode : "+this.subjectCode+",SubjectName : "+ this.subjectName+"]";
	}
}
