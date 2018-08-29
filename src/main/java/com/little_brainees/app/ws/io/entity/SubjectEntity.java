/**
 * 
 */
package com.little_brainees.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author ashish
 *
 */

@Entity(name = "subject")
public class SubjectEntity implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -117605805181533148L;
	
	
	@Id
	private String subjectCode;
	private String subjectName;
	
	@ManyToOne
	@JoinColumn(name = "class_code")
	ClassEntity classEntity; 
	
	/**
	 * @return the classEntity
	 */
	public ClassEntity getClassEntity() {
		return classEntity;
	}
	/**
	 * @param classEntity the classEntity to set
	 */
	public void setClassEntity(ClassEntity classEntity) {
		this.classEntity = classEntity;
	}
	/**
	 * @return the subjectCode
	 */
	public String getSubjectCode() {
		return subjectCode;
	}
	/**
	 * @param subjectCode the subjectCode to set
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	
	
}
