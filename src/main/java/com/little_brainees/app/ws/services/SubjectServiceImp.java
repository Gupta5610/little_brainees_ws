/**
 * 
 */
package com.little_brainees.app.ws.services;

import java.util.List;

import com.little_brainees.app.ws.DAO.BaseMySQLDAO;
import com.little_brainees.app.ws.DAO.DAO;
import com.little_brainees.app.ws.DTO.SubjectDTO;
import com.little_brainees.app.ws.exceptions.DuplicateRecordFoundException;
import com.little_brainees.app.ws.exceptions.ErrorMessages;
import com.little_brainees.app.ws.exceptions.RecordNotFoundException;
import com.little_brainees.app.ws.utilities.ValidationUtility;

/**
 * @author ashish
 *
 */
public class SubjectServiceImp implements SubjectService {

	/* (non-Javadoc)
	 * @see com.little_brainees.app.ws.services.SubjectService#createSubject(com.little_brainees.app.ws.DTO.SubjectDTO)
	 */
	
	public static SubjectService shared = new SubjectServiceImp();
	
	private DAO database = new BaseMySQLDAO();
	
	public SubjectDTO createSubject(SubjectDTO subjectDTO) {
       ValidationUtility.validateRequiredFieldsInDTO(subjectDTO);
		return this.saveSubject(subjectDTO);
	}
	
	
	private SubjectDTO saveSubject(SubjectDTO saveDTO) {
		SubjectDTO savedDTO = null;
		
		try {
			this.database.openConnection();
			savedDTO = this.database.saveSubject(saveDTO);
		}finally {
			this.database.closeConnection();
		}
		
		return savedDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.little_brainees.app.ws.services.SubjectService#getSubjects()
	 */
	
	public List<SubjectDTO> getSubjects() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.little_brainees.app.ws.services.SubjectService#getSubjectBy(java.lang.String)
	 */
	public SubjectDTO getSubjectBy(String subjectCode) {
		
		SubjectDTO subjectDTO = null;
		try {
			this.database.openConnection();
			subjectDTO = this.database.getSubjectBySubjectCode(subjectCode);
		}catch(Exception ex) {
			throw new RecordNotFoundException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
		}finally {
			this.database.closeConnection();
		}
		
		return subjectDTO;
	}

}
