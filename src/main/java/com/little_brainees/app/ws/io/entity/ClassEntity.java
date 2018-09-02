package com.little_brainees.app.ws.io.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.*;
import javax.persistence.OneToMany;


@Entity(name = "class")
public class ClassEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4689709049852742080L;
	
	@Id
	String classCode;
	String className;
	
	@OneToMany(mappedBy="classEntity")
	Collection<SubjectEntity> subjects = new ArrayList<SubjectEntity>();
	
	
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public void addSubjectEntity(SubjectEntity subjectEntity) {
		this.subjects.add(subjectEntity);
	}
	
	@Override
	public String toString() {
		return "Class [ ClassCode :"+ this.classCode+", ClassName : "+this.className+"]";
	}
}
