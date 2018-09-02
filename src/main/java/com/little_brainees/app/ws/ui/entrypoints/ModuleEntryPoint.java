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

import com.little_brainees.app.ws.DTO.ModuleDTO;
import com.little_brainees.app.ws.exceptions.ExceptionMapper;
import com.little_brainees.app.ws.services.ModuleService;
import com.little_brainees.app.ws.services.ModuleServiceImp;
import com.little_brainees.app.ws.utilities.ResponseBuilderUtil;

/**
 * @author ashish
 *
 */


@Path("/module")
public class ModuleEntryPoint {
	
	
	private ModuleService service;
	
	public ModuleEntryPoint() {
		this.service = new ModuleServiceImp();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CreateModule(ModuleDTO moduleDTO) {
		
	  ModuleDTO responseDTO = null;
	  
	  try {
		 responseDTO =  this.service.createModule(moduleDTO);
	  }catch(Exception ex) {
		  return ExceptionMapper.Response(ex);
	  }
		
		return ResponseBuilderUtil.createResponse(Status.CREATED, responseDTO);
	}
	
	

}
