/**
 * 
 */
package com.little_brainees.app.ws.ui.entrypoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.little_brainees.app.ws.DTO.ClassDTO;
import com.little_brainees.app.ws.DTO.TopicDTO;
import com.little_brainees.app.ws.exceptions.ExceptionMapper;
import com.little_brainees.app.ws.request.CreateTopicRequest;
import com.little_brainees.app.ws.services.IDatabaseService;
import com.little_brainees.app.ws.shared.RequestDTO;
import com.little_brainees.app.ws.services.DatabaseService;
import com.little_brainees.app.ws.utilities.ModelMapperUtil;
import com.little_brainees.app.ws.utilities.ResponseBuilderUtil;

/**
 * @author ashish
 *
 */

@Path("/topic")
public class TopicEntryPoint {
	
	
	private IDatabaseService service = DatabaseService.shared;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTopic(CreateTopicRequest requestOnject) {
		
		TopicDTO topicDTO = (TopicDTO)ModelMapperUtil.map(requestOnject, TopicDTO.class);
	    TopicDTO responseObject = null;
	    
	    try {
	    	responseObject = (TopicDTO)this.service.createEntity(topicDTO);
	    }catch(Exception ex) {
	    	return ExceptionMapper.Response(ex);
	    }
	    
		return ResponseBuilderUtil.createResponse(Status.CREATED, responseObject);
	}
	
	
	@Path("/{topicCode}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClassWith(@PathParam("topicCode") String topicCode) {
		
		TopicDTO createdDTO = null;
		
		try {
	    	createdDTO =(TopicDTO)this.service.getEntity(new RequestDTO(topicCode,TopicDTO.class));
	    }catch(Exception ex){
	    	return ExceptionMapper.Response(ex);
	    }
		return ResponseBuilderUtil.createResponse(Status.FOUND, createdDTO);
	}

}
