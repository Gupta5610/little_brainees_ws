/**
 * 
 */
package com.little_brainees.app.ws.io.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author ashish
 *
 */

@Entity(name = "child")
public class ChildEntity {
	
    @Id
	String childId;
    String childName;
    String childSchool;
    
    @ManyToOne
    @JoinColumn(name = "teacherID")
    TeacherEntity teacher;
    
    @ManyToOne
    @JoinColumn(name = "parentID")
    ParentEntity parent;
    
    @ManyToOne
    @JoinColumn(name = "classCode")
    ClassEntity childClass;

	/**
	 * @return the childClass
	 */
	public ClassEntity getChildClass() {
		return childClass;
	}

	/**
	 * @param childClass the childClass to set
	 */
	public void setChildClass(ClassEntity childClass) {
		this.childClass = childClass;
	}

	/**
	 * @return the childId
	 */
	public String getChildID() {
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
	 * @return the teacher
	 */
	public TeacherEntity getTeacher() {
		return teacher;
	}

	/**
	 * @return the parent
	 */
	public ParentEntity getParent() {
		return parent;
	}

	/**
	 * @param childId the childId to set
	 */
	public void setChildID(String childID) {
		this.childId = childID;
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
	 * @param teacher the teacher to set
	 */
	public void setTeacher(TeacherEntity teacher) {
		this.teacher = teacher;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(ParentEntity parent) {
		this.parent = parent;
	}
    
}
