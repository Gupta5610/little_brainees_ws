package com.little_brainees.app.ws.ui.entrypoints;

import javax.ws.rs.Path;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.little_brainees.app.ws.DTO.ClassDTO;
import com.little_brainees.app.ws.exceptions.ExceptionMapper;
import com.little_brainees.app.ws.request.CreateClassRequest;
import com.little_brainees.app.ws.response.CreateClassResponse;
import com.little_brainees.app.ws.services.ClassService;
import com.little_brainees.app.ws.services.ClassServiceImp;
import com.little_brainees.app.ws.utilities.ModelMapperUtil;
import com.little_brainees.app.ws.utilities.ResponseBuilderUtil;



@Path("/classes")
public class ClassEntryPoint {
	
	
	ClassService service = ClassServiceImp.shared;
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response createClass(CreateClassRequest requestObject) {
    	
	    ClassDTO classDTO = ModelMapperUtil.shared.getMapper().map(requestObject, ClassDTO.class);
	    ClassDTO createdDTO;
	    try {
	    	createdDTO = this.service.createClass(classDTO);
	    }catch(Exception ex){
	    	return ExceptionMapper.Response(ex);
	    }
	    
	    CreateClassResponse responseObject = new CreateClassResponse(createdDTO.getClassCode());
		return ResponseBuilderUtil.createResponse(Status.CREATED,responseObject);
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClasses() {
		System.out.println("getClasses function called");
		ArrayList<String> returnObject = new ArrayList<String>();
		returnObject.add("first");
		returnObject.add("second");
		System.out.println("getCLass");
		return ResponseBuilderUtil.createResponse(Status.FOUND, returnObject);
    } 
}
