/**
 * 
 */
package com.little_brainees.app.ws.request;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ashish
 *
 */

@XmlRootElement
public class CreateTopicRequest {

	String topicCode;
	String topicName;
    String moduleCode;
	
	
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
