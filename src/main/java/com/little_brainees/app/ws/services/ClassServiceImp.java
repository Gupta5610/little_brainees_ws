/**
 * 
 */
package com.little_brainees.app.ws.services;

import java.util.Collection;

import com.little_brainees.app.ws.DAO.DAO;
import com.little_brainees.app.ws.DAO.BaseMySQLDAO;
import com.little_brainees.app.ws.DTO.ClassDTO;
import com.little_brainees.app.ws.exceptions.ErrorMessages;
import com.little_brainees.app.ws.exceptions.MissingRequiredFieldException;
import com.little_brainees.app.ws.exceptions.RecordNotFoundException;
import com.little_brainees.app.ws.utilities.ValidationUtility;

/**
 * @author ashish
 *
 */
public class ClassServiceImp implements ClassService {

	/* (non-Javadoc)
	 * @see com.little_brainees.app.ws.services.ClassService#createClass(com.little_brainees.app.ws.DTO.ClassDTO)
	 */
	
	public static ClassService shared = new ClassServiceImp();
	
	DAO database = new BaseMySQLDAO();
	
	private ClassServiceImp() {
		
	}
	
	
	@Override
	public ClassDTO createClass(ClassDTO classDTO) throws MissingRequiredFieldException {
		ValidationUtility.validateRequiredFieldsInDTO(classDTO);
		return this.saveClass(classDTO);
	}
	
	private ClassDTO saveClass(ClassDTO classDTO) {
		try {
			this.database.openConnection();	
			classDTO = this.database.saveClass(classDTO);
		}finally {
			this.database.closeConnection();
		}
		return classDTO;
	}
	
	
	public ClassDTO getClassByClassCode(String classCode) {
		ClassDTO classDTO = null;
		try {
			this.database.openConnection();
			classDTO = this.database.getClassByClassCode(classCode);
		}catch(Exception ex) {
			throw new RecordNotFoundException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		}finally {
			this.database.closeConnection();
		}
		
		return classDTO;
	}

	/* (non-Javadoc)
	 * @see com.little_brainees.app.ws.services.ClassService#getClassby(java.lang.String)
	 */
	@Override
	public ClassDTO getClassby(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.little_brainees.app.ws.services.ClassService#getAllClass()
	 */
	@Override
	public Collection<ClassDTO> getAllClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
