/**
 * 
 */
package com.little_brainees.app.ws.DTO;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ashish
 *
 */

@XmlRootElement 
public class ClassDTO  implements Serializable,BaseDTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 994497485706610783L;
	String classCode;
    String className;
    
    List<SubjectDTO> subjects;
    
    
    /**
	 * @return the subjects
	 */
	public List<SubjectDTO> getSubjects() {
		return subjects;
	}

	/**
	 * @param subjects the subjects to set
	 */
	public void setSubjects(List<SubjectDTO> subjects) {
		this.subjects = subjects;
	}

	public ClassDTO() {
    	
    }
    
    public ClassDTO(String classCode , String className) {
    	this.classCode = classCode;
    	this.className = className;
    }
    
  
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
	
	
	@Override
	public String toString() {
		return "Class [ ClassCode :"+ this.classCode+", ClassName : "+this.className+"]";
	}
}
