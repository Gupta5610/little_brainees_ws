package com.little_brainees.app.ws.DTO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class ModuleDTO  implements Serializable,BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2594289879559143616L;

	@Transient
	String subjectCode;
	
	
	String moduleCode;
	String moduleName;
	
	List<TopicDTO> topics;
	
	/**
	 * @return the topics
	 */
	public List<TopicDTO> getTopics() {
		return topics;
	}
	/**
	 * @param topics the topics to set
	 */
	public void setTopics(List<TopicDTO> topics) {
		this.topics = topics;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	@Override
	public String toString() {
		return "Module [ModuleCode : "+this.moduleCode+",ModuleName : "+this.moduleName+"]";
	}
	
public ModuleDTO() {
		
	}
	
	public ModuleDTO( String subjectCode,String moduleCode,String moduleName){
	
		this.subjectCode = subjectCode;
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
	}
	
}
