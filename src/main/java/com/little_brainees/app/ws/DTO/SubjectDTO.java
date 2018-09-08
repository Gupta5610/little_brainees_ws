package com.little_brainees.app.ws.DTO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;



public class SubjectDTO  implements Serializable , BaseDTO {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Transient
	@Column(name = "ClassCode")
	String classCode;
	String subjectCode;
	String subjectName;
	
	List<ModuleDTO> modules;
	
	/**
	 * @return the modules
	 */
	public List<ModuleDTO> getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	public void setModules(List<ModuleDTO> modules) {
		this.modules = modules;
	}

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
