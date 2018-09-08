package com.little_brainees.app.ws.ui.entrypoints;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.little_brainees.app.ws.DTO.BaseDTO;
import com.little_brainees.app.ws.DTO.ClassDTO;
import com.little_brainees.app.ws.DTO.SubjectDTO;
import com.little_brainees.app.ws.exceptions.ExceptionMapper;
import com.little_brainees.app.ws.request.CreateClassRequest;
import com.little_brainees.app.ws.response.CreateClassResponse;
import com.little_brainees.app.ws.services.DatabaseService;
import com.little_brainees.app.ws.services.IDatabaseService;
import com.little_brainees.app.ws.shared.RequestDTO;
import com.little_brainees.app.ws.utilities.ModelMapperUtil;
import com.little_brainees.app.ws.utilities.ResponseBuilderUtil;



@Path("/class")
public class ClassEntryPoint {
	
	
	IDatabaseService service = DatabaseService.shared;
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response createClass(CreateClassRequest requestObject) {
    	
	    ClassDTO classDTO = (ClassDTO)ModelMapperUtil.map(requestObject, ClassDTO.class);
	    ClassDTO createdDTO;
	    try {
	    	createdDTO =(ClassDTO)this.service.createEntity(classDTO);
	    }catch(Exception ex){
	    	return ExceptionMapper.Response(ex);
	    }
	    
	    CreateClassResponse responseObject = new CreateClassResponse(createdDTO.getClassCode());
		return ResponseBuilderUtil.createResponse(Status.CREATED,responseObject);
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClasses() {
		List<ClassDTO> resultList = new ArrayList<ClassDTO>();
		try {
			for(BaseDTO dto : this.service.getAllEntity(new RequestDTO("as",ClassDTO.class))) {
				resultList.add((ClassDTO)dto);
			}
		}catch(Exception ex) {
			return ExceptionMapper.Response(ex);
		}
		
		return ResponseBuilderUtil.createResponse(Status.FOUND, resultList);
    } 
	
	
	@Path("/{classCode}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClassWith(@PathParam("classCode") String classCode) {
		
		ClassDTO createdDTO = null;
		
		try {
	    	createdDTO =(ClassDTO)this.service.getEntity(new RequestDTO(classCode,ClassDTO.class));
	    }catch(Exception ex){
	    	return ExceptionMapper.Response(ex);
	    }
		return ResponseBuilderUtil.createResponse(Status.FOUND, createdDTO);
	}
	
	
	@Path("/{classCode}/subject")
	@Produces(MediaType.APPLICATION_JSON)
	public SubjectEntryPoint getSubjectResource(){
		return new SubjectEntryPoint();
	}
}
