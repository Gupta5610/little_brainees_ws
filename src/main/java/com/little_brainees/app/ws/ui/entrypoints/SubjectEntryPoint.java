package com.little_brainees.app.ws.ui.entrypoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.little_brainees.app.ws.DTO.SubjectDTO;
import com.little_brainees.app.ws.exceptions.ExceptionMapper;
import com.little_brainees.app.ws.request.CreateSubjectRequest;
import com.little_brainees.app.ws.services.SubjectService;
import com.little_brainees.app.ws.services.SubjectServiceImp;
import com.little_brainees.app.ws.utilities.ModelMapperUtil;
import com.little_brainees.app.ws.utilities.ResponseBuilderUtil;

@Path("/subject")
public class SubjectEntryPoint {
 
	SubjectService service;
	
	public SubjectEntryPoint() {
		this.service = SubjectServiceImp.shared;
	}
	
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response createSubject(CreateSubjectRequest requestObject) {
		SubjectDTO requestDTO = ModelMapperUtil.shared.getMapper().map(requestObject, SubjectDTO.class);
		SubjectDTO responseDTO ;
		try {
			responseDTO = this.service.createSubject(requestDTO);
		}catch(Exception ex){
			return ExceptionMapper.Response(ex);
		}
		
    	return ResponseBuilderUtil.createResponse(Status.CREATED, responseDTO);
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubjectWith() {
		
		SubjectDTO subjectDTO = null;
		try {
			subjectDTO = this.service.getSubjectBy("SUBENG0");
		}catch(Exception ex) {
			return ExceptionMapper.Response(ex);
		}
		return ResponseBuilderUtil.createResponse(Status.FOUND, subjectDTO);
	}
	
}
