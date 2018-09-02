/**
 * 
 */
package com.little_brainees.app.ws.services;


import com.little_brainees.app.ws.DAO.DAO;
import com.little_brainees.app.ws.DAO.BaseMySQLDAO;
import com.little_brainees.app.ws.DTO.TeacherDTO;
import com.little_brainees.app.ws.exceptions.DuplicateRecordFoundException;
import com.little_brainees.app.ws.exceptions.ErrorMessages;
import com.little_brainees.app.ws.exceptions.RecordNotFoundException;
import com.little_brainees.app.ws.utilities.*;;


/**
 * @author ashish
 *
 */
public class TeacherServiceImp implements TeacherService {
	
	public static TeacherServiceImp shared = new TeacherServiceImp();
	
	DAO database = new BaseMySQLDAO();
	
	private TeacherServiceImp() {
		
	}

	public TeacherDTO CreateTeacher(TeacherDTO teacherDTO){
		ValidationUtility.validateRequiredFieldsInDTO(teacherDTO);	
		    return this.saveTeacher(teacherDTO);
	}
	
	private TeacherDTO saveTeacher(TeacherDTO teacherDTO) {
		
		TeacherDTO resultDTO = null;
		try {
			this.database.openConnection();
			resultDTO = this.database.saveTeacher(teacherDTO);
		}catch(Exception ex){
			if (ex instanceof RuntimeException) {
				throw ex;
			}
		}finally {
			this.database.closeConnection();
		}
		
		return resultDTO;
	}
	
	
	public TeacherDTO getTeacherByEmail(String email) {
		
		TeacherDTO teacherDTO = null;
	
		
		if(email == null || email.isEmpty()) {
			return teacherDTO;
		}
		
		try {
			this.database.openConnection();
			teacherDTO = this.database.getTeacherByEmail(email);
		}catch(Exception ex) {
			
		}
		finally {
			this.database.closeConnection();
		}
		
		
		
		return teacherDTO;
	}
	
	public TeacherDTO randomteacher() throws RecordNotFoundException {
		TeacherDTO responseObject = null;
		
		responseObject =  this.getTeacherByEmail("ashucooldude5610@gmail.com");
		
		if(responseObject == null) {
			throw new RecordNotFoundException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		}
		
		return responseObject;
		
	}
	
}
