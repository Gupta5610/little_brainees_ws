/**
 * 
 */
package com.little_brainees.app.ws.io.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author ashish
 *
 */

@Entity(name = "MODULE")
public class ModuleEntity {
     
	@Id
	String moduleCode;
	String moduleName;
	
	@ManyToOne
	@JoinColumn(name = "Subject_Code")
	SubjectEntity subject;
	
	
	@OneToMany(mappedBy = "module")
	List<TopicEntity> topics;
	
	/**
	 * @return the moduleCode
	 */
	public String getModuleCode() {
		return moduleCode;
	}

	/**
	 * @param moduleCode the moduleCode to set
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	/**
	 * @return the subjectCode
	 */
	
	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the subject
	 */
	public SubjectEntity getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}
	
    public void addTopic(TopicEntity entity) {
    	this.topics.add(entity);
    }
    
    @Override
	public String toString() {
		return "Module [ModuleCode : "+this.moduleCode+",ModuleName : "+this.moduleName+"]";
	}
    
    public List<TopicEntity> getTopics(){
    	return this.topics;
    }
}
