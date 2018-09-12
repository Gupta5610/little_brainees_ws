/**
 * 
 */
package com.little_brainees.app.ws.ui.entrypoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.little_brainees.app.ws.DTO.ChildDTO;
import com.little_brainees.app.ws.exceptions.ExceptionMapper;
import com.little_brainees.app.ws.request.ChildRequestObject;
import com.little_brainees.app.ws.response.ChildResponseDTO;
import com.little_brainees.app.ws.services.DatabaseService;
import com.little_brainees.app.ws.services.IDatabaseService;
import com.little_brainees.app.ws.shared.RequestDTO;
import com.little_brainees.app.ws.utilities.ModelMapperUtil;
import com.little_brainees.app.ws.utilities.ResponseBuilderUtil;

/**
 * @author ashish
 *
 */


@Path("/child")
public class ChildEntryPoint {
	
	private IDatabaseService service = DatabaseService.shared;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createChild(ChildRequestObject requestObject) {
		ChildDTO childDTO = (ChildDTO)ModelMapperUtil.map(requestObject, ChildDTO.class);
		ChildResponseDTO responseObject = null;
		try {
			responseObject = (ChildResponseDTO)this.service.createEntity(childDTO);
		}catch(Exception ex) {
			return ExceptionMapper.Response(ex);
		}
		
		return ResponseBuilderUtil.createResponse(Status.CREATED, responseObject);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateChild(ChildRequestObject requestObject) {
		System.out.println("updade post");
		ChildDTO childDTO = (ChildDTO)ModelMapperUtil.map(requestObject, ChildDTO.class);
		ChildResponseDTO responseObject = null;
		try {
			responseObject = (ChildResponseDTO)this.service.updateEntity(childDTO);
		}catch(Exception ex) {
			return ExceptionMapper.Response(ex);
		}
		
		return ResponseBuilderUtil.createResponse(Status.CREATED, responseObject);
	}
	
	
	@Path("/{childId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChild(@PathParam("childId")String childId) {
		
		ChildResponseDTO resultDTO = null;
		 try {
			 Object ob = (ChildResponseDTO)this.service.getEntity(new RequestDTO(childId,ChildResponseDTO.class));
		 }catch(Exception ex) {
			 return ExceptionMapper.Response(ex);
		 }
		 return ResponseBuilderUtil.createResponse(Status.FOUND, resultDTO);
	}

}
