package com.little_brainees.app.ws.ui.entrypoints;

import com.little_brainees.app.ws.services.TeacherService;
import com.little_brainees.app.ws.services.TeacherServiceImp;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



@Path("/users")
public class TeacherEntryPoint {
	
	
	TeacherService teacherService ;
	
	public TeacherEntryPoint() {
		this.teacherService = TeacherServiceImp.shared;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
   public Response createTeacher(CreateTeacherRequest requestObject) {
		TeacherDTO teacherDTO = (new ObjectMapper()).convertValue(requestObject, TeacherDTO.class);
		TeacherDTO createdDTO ;
		try {
			  createdDTO =  this.teacherService.CreateTeacher(teacherDTO);
		}catch(Exception ex) {
			return ExceptionMapper.Response(ex);
		}
			
	    return ResponseBuilderUtil.createResponse(Status.CREATED, createdDTO);
   } 
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
   public Response getTeachers() {
    	TeacherDTO teacherDTO;
    	try {
         teacherDTO = TeacherServiceImp.shared.randomteacher();
    	}catch(Exception ex) {
    		return ExceptionMapper.Response(ex);
    	}
    	
	   return  ResponseBuilderUtil.createResponse(Status.OK, teacherDTO);
   }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updateTeacher() {
	  
   }
   
}
