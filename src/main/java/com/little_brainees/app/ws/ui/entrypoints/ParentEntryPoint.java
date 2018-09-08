/**
 * 
 */
package com.little_brainees.app.ws.ui.entrypoints;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.little_brainees.app.ws.DTO.ParentDTO;
import com.little_brainees.app.ws.exceptions.ExceptionMapper;
import com.little_brainees.app.ws.request.ParentRequestObject;
import com.little_brainees.app.ws.services.DatabaseService;
import com.little_brainees.app.ws.services.IDatabaseService;
import com.little_brainees.app.ws.shared.RequestDTO;
import com.little_brainees.app.ws.utilities.ModelMapperUtil;
import com.little_brainees.app.ws.utilities.ResponseBuilderUtil;

/**
 * @author ashish
 *
 */


@Path("/parent")
public class ParentEntryPoint {

	private IDatabaseService service = DatabaseService.shared;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createParent(ParentRequestObject requestObject) {
		ParentDTO resultDTO = (ParentDTO)ModelMapperUtil.map(requestObject, ParentDTO.class);
		
		try {
			resultDTO = (ParentDTO)this.service.createEntity(resultDTO);
		}catch(Exception ex) {
			return ExceptionMapper.Response(ex);
		}
		return ResponseBuilderUtil.createResponse(Status.CREATED, resultDTO);
	}
	
	@Path("/{parentID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getParent(@PathParam("parentID") String parentID) {
		
		ParentDTO parentDTO = null;
				
				try {
					parentDTO = (ParentDTO)this.service.getEntity(new RequestDTO(parentID,ParentDTO.class));
				}catch(Exception ex) {
					return ExceptionMapper.Response(ex);
				}
		return ResponseBuilderUtil.createResponse(Status.FOUND, parentDTO);
	}
}
