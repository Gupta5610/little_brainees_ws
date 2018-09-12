package com.little_brainees.app.ws.ui.entrypoints;

import com.little_brainees.app.ws.services.DatabaseService;
import com.little_brainees.app.ws.services.IDatabaseService;
import com.little_brainees.app.ws.shared.RequestDTO;
import com.little_brainees.app.ws.utilities.ResponseBuilderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.little_brainees.app.ws.DTO.TeacherDTO;
import com.little_brainees.app.ws.exceptions.ExceptionMapper;
import com.little_brainees.app.ws.request.*;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



@Path("/teacher")
public class TeacherEntryPoint {
	
	
	IDatabaseService teacherService ;
	
	public TeacherEntryPoint() {
		this.teacherService = DatabaseService.shared;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
   public Response createTeacher(TeacherRequestObject requestObject) {
		requestObject.setIsActive(false);
		TeacherDTO teacherDTO = (new ObjectMapper()).convertValue(requestObject, TeacherDTO.class);
		
		TeacherDTO createdDTO ;
		try {
			  createdDTO = (TeacherDTO)this.teacherService.createEntity(teacherDTO);
		}catch(Exception ex) {
			return ExceptionMapper.Response(ex);
		}
			
	    return ResponseBuilderUtil.createResponse(Status.CREATED, createdDTO);
   } 
	
    @Path("/{teacherID}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
   public Response getTeacher(@PathParam("teacherID")  String teacherID) {
    	TeacherDTO teacherDTO = null;
    	try {
         teacherDTO = (TeacherDTO)this.teacherService.getEntity(new RequestDTO(teacherID,TeacherDTO.class));
    	}catch(Exception ex) {
    		return ExceptionMapper.Response(ex);
    	}
    	
	   return  ResponseBuilderUtil.createResponse(Status.OK, teacherDTO);
   }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeacher(TeacherRequestObject requestObject) {
    	TeacherDTO teacherDTO = (new ObjectMapper()).convertValue(requestObject, TeacherDTO.class);
		TeacherDTO createdDTO ;
		try {
			  createdDTO = (TeacherDTO)this.teacherService.updateEntity(teacherDTO);
		}catch(Exception ex) {
			return ExceptionMapper.Response(ex);
		}
			
	    return ResponseBuilderUtil.createResponse(Status.CREATED, createdDTO);
   }
   
}
