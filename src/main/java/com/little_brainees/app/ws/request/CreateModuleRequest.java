/**
 * 
 */
package com.little_brainees.app.ws.request;

import javax.persistence.Transient;

/**
 * @author ashish
 *
 */
public class CreateModuleRequest {

	String subjectCode;
	String moduleCode;
	String moduleName;
	
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
		return "Module Request Object [ModuleCode : "+this.moduleCode+",ModuleName : "+this.moduleName+"]";
	}
}
