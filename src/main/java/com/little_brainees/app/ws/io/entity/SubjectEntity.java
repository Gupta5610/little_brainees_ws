/**
 * 
 */
package com.little_brainees.app.ws.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	
	@OneToMany(mappedBy="subject")
	List<ModuleEntity> modules;
	
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
	
	public void addModuleEntity(ModuleEntity entity) {
		this.modules.add(entity);
	}

	@Override
	public String toString() {
		return "Subject [SubjectCode : "+this.subjectCode+",SubjectName : "+ this.subjectName+"]";
	}
	
	
	public List<ModuleEntity> getModules(){
		return this.modules;
	}
	
	
}
