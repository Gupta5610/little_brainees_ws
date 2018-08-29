/**
 * 
 */
package com.little_brainees.app.ws.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ashish
 *
 */

@XmlRootElement
public class CreateSubjectRequest {
	
	String subjectCode;
	String classCode;
	String subjectName;
	
	
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
	 * @return the classCode
	 */
	public String getClassCode() {
		return classCode;
	}
	/**
	 * @param classCode the classCode to set
	 */
	public void setClassCode(String classCode) {
		this.classCode = classCode;
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
