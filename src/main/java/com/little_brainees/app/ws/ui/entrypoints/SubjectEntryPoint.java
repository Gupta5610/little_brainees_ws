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
import com.little_brainees.app.ws.DTO.ModuleDTO;
import com.little_brainees.app.ws.DTO.SubjectDTO;
import com.little_brainees.app.ws.exceptions.ExceptionMapper;
import com.little_brainees.app.ws.request.CreateSubjectRequest;
import com.little_brainees.app.ws.services.DatabaseService;
import com.little_brainees.app.ws.services.IDatabaseService;
import com.little_brainees.app.ws.shared.RequestDTO;
import com.little_brainees.app.ws.utilities.LBLogger;
import com.little_brainees.app.ws.utilities.ModelMapperUtil;
import com.little_brainees.app.ws.utilities.ResponseBuilderUtil;

@Path("/subject")
public class SubjectEntryPoint {
 
	IDatabaseService service;
	
	public SubjectEntryPoint() {
		this.service = DatabaseService.shared;
	}
	
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response createSubject(CreateSubjectRequest requestObject) {
		SubjectDTO requestDTO = (SubjectDTO)ModelMapperUtil.map(requestObject, SubjectDTO.class);
		SubjectDTO responseDTO ;
		try {
			responseDTO = (SubjectDTO)this.service.createEntity(requestDTO);
		}catch(Exception ex){
			return ExceptionMapper.Response(ex);
		}
		
    	return ResponseBuilderUtil.createResponse(Status.CREATED, responseDTO);
    }
	
	@Path("/{subjectCode}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClassWith(@PathParam("subjectCode") String subjectCode) {
		
		SubjectDTO createdDTO = null;
		
		try {
	    	createdDTO =(SubjectDTO)this.service.getEntity(new RequestDTO(subjectCode,SubjectDTO.class));
	    }catch(Exception ex){
	    	return ExceptionMapper.Response(ex);
	    }
		return ResponseBuilderUtil.createResponse(Status.FOUND, createdDTO);
	}
	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSubjects(@PathParam("classCode") String classCode) {
		List<SubjectDTO> resultList = new ArrayList<SubjectDTO>();
		LBLogger.logMessage("getAllClassessCalled()");
		try {
		     for(BaseDTO dto : this.service.getAllEntity(new RequestDTO(classCode,SubjectDTO.class))) {
		    	 resultList.add((SubjectDTO)dto);
		     }
		}catch(Exception ex) {
			return ExceptionMapper.Response(ex);
		}
		return ResponseBuilderUtil.createResponse(Status.FOUND, resultList);
	}
	
	
	
      @Path("/{subjectCode}/module")
      @Produces(MediaType.APPLICATION_JSON)
     public ModuleEntryPoint getModuleResource(){
    	return new ModuleEntryPoint();
      }
      
}
	

