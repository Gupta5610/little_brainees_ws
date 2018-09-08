/**
 * 
 */
package com.little_brainees.app.ws.request;

import javax.xml.bind.annotation.XmlRootElement;

import com.little_brainees.app.ws.DTO.ParentDTO;
import com.little_brainees.app.ws.DTO.TeacherDTO;

/**
 * @author ashish
 *
 */

@XmlRootElement
public class ChildRequestObject {
	
	String childID;
    String childName;
    String childSchool;
    String childClassCode;
    String parentID;
    String teacherID;
    /**
	 * @return the parentID
	 */
	public String getParentID() {
		return parentID;
	}
	/**
	 * @return the teacherID
	 */
	public String getTeacherID() {
		return teacherID;
	}
	/**
	 * @param parentID the parentID to set
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	/**
	 * @param teacherID the teacherID to set
	 */
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	
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
	 * @return the childClassCode
	 */
	public String getChildClassCode() {
		return childClassCode;
	}
	/**
	 * @return the parent
	 */
	
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
	 * @param childClassCode the childClassCode to set
	 */
	public void setChildClassCode(String childClassCode) {
		this.childClassCode = childClassCode;
	}
	/**
	 * @param parent the parent to set
	 */
	

}
