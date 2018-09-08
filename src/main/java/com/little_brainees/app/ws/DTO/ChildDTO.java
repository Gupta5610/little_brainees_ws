/**
 * 
 */
package com.little_brainees.app.ws.DTO;

/**
 * @author ashish
 *
 */
public class ChildDTO implements BaseDTO {
	
	String childId;
    String childName;
    String childSchool;
    String childClassCode;
    String parentId;
    String teacherId;
	/**
	 * @return the childId
	 */
	public String getChildId() {
		return childId;
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
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @return the teacherId
	 */
	public String getTeacherId() {
		return teacherId;
	}
	/**
	 * @param childId the childId to set
	 */
	public void setChildId(String childId) {
		this.childId = childId;
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
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * @param teacherId the teacherId to set
	 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
    
   
}
