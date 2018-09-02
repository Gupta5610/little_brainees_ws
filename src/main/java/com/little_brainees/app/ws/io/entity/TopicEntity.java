package com.little_brainees.app.ws.io.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name = "Topic")
public class TopicEntity {

	@Id
	 String topicCode;
     String topicName;
	
	@ManyToOne
	@JoinColumn(name = "Module_Code")
	 ModuleEntity module;
	/**
	 * @return the topicID
	 */
	public String getTopicCode() {
		return topicCode;
	}

	/**
	 * @param topicID the topicID to set
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
	 * @return the entity
	 */
	public ModuleEntity getEntity() {
		return module;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(ModuleEntity entity) {
		this.module = entity;
	}

	@Override
	public String toString() {
		return "Topic : [TopicCode : "+this.topicCode+", TopicName : "+this.topicName+"]";
	}
}
