/**
 * 
 */
package com.little_brainees.app.ws.ui.entrypoints;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.little_brainees.app.ws.DTO.BaseDTO;
import com.little_brainees.app.ws.DTO.ClassDTO;
import com.little_brainees.app.ws.DTO.ModuleDTO;
import com.little_brainees.app.ws.DTO.SubjectDTO;
import com.little_brainees.app.ws.exceptions.ExceptionMapper;
import com.little_brainees.app.ws.services.DatabaseService;
import com.little_brainees.app.ws.services.IDatabaseService;
import com.little_brainees.app.ws.shared.RequestDTO;
import com.little_brainees.app.ws.utilities.LBLogger;
import com.little_brainees.app.ws.utilities.ResponseBuilderUtil;

/**
 * @author ashish
 *
 */


@Path("/module")
public class ModuleEntryPoint {
	
	
	private IDatabaseService service;
	
	public ModuleEntryPoint() {
		this.service = DatabaseService.shared;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CreateModule(ModuleDTO moduleDTO) {
		
	  ModuleDTO responseDTO = null;
	  
	  try {
		 responseDTO =  (ModuleDTO)this.service.createEntity(moduleDTO);
	  }catch(Exception ex) {
		  return ExceptionMapper.Response(ex);
	  }
		
		return ResponseBuilderUtil.createResponse(Status.CREATED, responseDTO);
	}
	
	@Path("/{moduleCode}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClassWith(@PathParam("moduleCode") String moduleCode) {
		
		ModuleDTO createdDTO = null;
		
		try {
	    	createdDTO =(ModuleDTO)this.service.getEntity(new RequestDTO(moduleCode,ModuleDTO.class));
	    }catch(Exception ex){
	    	return ExceptionMapper.Response(ex);
	    }
		return ResponseBuilderUtil.createResponse(Status.FOUND, createdDTO);
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getModuleWithSubjectCode(@PathParam("subjectCode") String subjectCode) {
		
		List<ModuleDTO> resultList = new ArrayList<ModuleDTO>();
		LBLogger.logMessage("getAllClassessCalled()");
		try {
		     for(BaseDTO dto : this.service.getAllEntity(new RequestDTO(subjectCode,ModuleDTO.class))) {
		    	 resultList.add((ModuleDTO)dto);
		     }
		}catch(Exception ex) {
			return ExceptionMapper.Response(ex);
		}
		return ResponseBuilderUtil.createResponse(Status.FOUND, resultList);
	}
	
	
	@Path("/{moduleCode}/topic")
	@Produces(MediaType.APPLICATION_JSON)
	public TopicEntryPoint getAllTopic() {
		return new TopicEntryPoint();
	}
	

}
