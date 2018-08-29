package com.little_brainees.app.ws.DTO;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class ModuleDTO extends BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2594289879559143616L;

	String subjectCode;
	String moduleCode;
	String moduleName;
	
	
	public ModuleDTO() {
		
	}
	
	public ModuleDTO( String subjectCode,String moduleCode,String moduleName){
	
		this.subjectCode = subjectCode;
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
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
	
}
