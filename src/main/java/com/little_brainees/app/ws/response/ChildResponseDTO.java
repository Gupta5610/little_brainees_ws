/**
 * 
 */
package com.little_brainees.app.ws.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.little_brainees.app.ws.DTO.BaseDTO;
import com.little_brainees.app.ws.DTO.ClassDTO;
import com.little_brainees.app.ws.DTO.ParentDTO;
import com.little_brainees.app.ws.DTO.TeacherDTO;

/**
 * @author ashish
 *
 */

public class ChildResponseDTO implements Serializable, BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5024440120900986804L;
	
	String childID;
    String childName;
    String childSchool;
    ParentDTO parent;
    TeacherDTO teacher;
    ClassDTO childClass;
	/**
	 * @return the childId
	 */
	public String getChildID() {
		return childID;
	}
	/**
	 * @return the childName
	 */
	public String getChildName() {
		return childName;
	}
	/**
	 * @return the childSchool
	 */
	public String getChildSchool() {
		return childSchool;
	}
	/**
	 * @return the parent
	 */
	public ParentDTO getParent() {
		return parent;
	}
	/**
	 * @return the teacher
	 */
	public TeacherDTO getTeacher() {
		return teacher;
	}
	/**
	 * @return the childClass
	 */
	public ClassDTO getChildClass() {
		return childClass;
	}
	/**
	 * @param childId the childId to set
	 */
	public void setChildID(String childID) {
		this.childID = childID;
	}
	/**
	 * @param childName the childName to set
	 */
	public void setChildName(String childName) {
		this.childName = childName;
	}
	/**
	 * @param childSchool the childSchool to set
	 */
	public void setChildSchool(String childSchool) {
		this.childSchool = childSchool;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(ParentDTO parent) {
		this.parent = parent;
	}
	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(TeacherDTO teacher) {
		this.teacher = teacher;
	}
	/**
	 * @param childClass the childClass to set
	 */
	public void setChildClass(ClassDTO childClass) {
		this.childClass = childClass;
	}
    
    
}
