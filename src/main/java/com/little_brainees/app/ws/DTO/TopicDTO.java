/**
 * 
 */
package com.little_brainees.app.ws.DTO;


import java.io.Serializable;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ashish
 *
 */

@XmlRootElement
public class TopicDTO implements Serializable,BaseDTO{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 2019583084923132196L;
	String topicCode;
	String topicName;
	
	@Transient
	private String moduleCode;
	
	
	/**
	 * @return the topicCode
	 */
	public String getTopicCode() {
		return topicCode;
	}
	/**
	 * @param topicCode the topicCode to set
	 */
	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}
	/**
	 * @return the topicName
	 */
	public String getTopicName() {
		return topicName;
	}
	/**
	 * @param topicName the topicName to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
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
	
	@Override
	public String toString() {
		return "Topic : [TopicCode : "+this.topicCode+", TopicName : "+this.topicName+"]";
	}
	
}
