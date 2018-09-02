/**
 * 
 */
package com.little_brainees.app.ws.services;

import java.util.List;

import com.little_brainees.app.ws.DTO.TopicDTO;

/**
 * @author ashish
 *
 */
public interface TopicService {
	
	public TopicDTO createTopic(TopicDTO topicDTO);
	public List<TopicDTO> getTopics();
	public TopicDTO getTopictBy(String topicCode);

}
