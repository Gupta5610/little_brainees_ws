/**
 * 
 */
package com.little_brainees.app.ws.ui.entrypoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.little_brainees.app.ws.DTO.TopicDTO;
import com.little_brainees.app.ws.exceptions.ExceptionMapper;
import com.little_brainees.app.ws.request.CreateTopicRequest;
import com.little_brainees.app.ws.services.TopicService;
import com.little_brainees.app.ws.services.TopicServiceImp;
import com.little_brainees.app.ws.utilities.ModelMapperUtil;
import com.little_brainees.app.ws.utilities.ResponseBuilderUtil;

/**
 * @author ashish
 *
 */

@Path("/topic")
public class TopicEntryPoint {
	
	private TopicService service = new TopicServiceImp();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTopic(CreateTopicRequest requestOnject) {
		
		TopicDTO topicDTO = (TopicDTO)ModelMapperUtil.map(requestOnject, TopicDTO.class);
	    TopicDTO responseObject = null;
	    
	    try {
	    	responseObject = this.service.createTopic(topicDTO);
	    }catch(Exception ex) {
	    	return ExceptionMapper.Response(ex);
	    }
	    
	   
		return ResponseBuilderUtil.createResponse(Status.CREATED, responseObject);
	}

}
