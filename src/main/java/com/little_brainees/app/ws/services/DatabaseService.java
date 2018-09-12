/**
 * 
 */
package com.little_brainees.app.ws.services;

import java.util.List;

import com.little_brainees.app.ws.DAO.*;
import com.little_brainees.app.ws.DTO.BaseDTO;
import com.little_brainees.app.ws.shared.RequestDTO;
import com.little_brainees.app.ws.utilities.LBLogger;
import com.little_brainees.app.ws.utilities.ValidationUtility;

/**
 * @author ashish
 *
 */
public class DatabaseService implements IDatabaseService {

    public static IDatabaseService shared = new DatabaseService();
    
	private IDAO database = new DAO();
	
	private DatabaseService(){
		
	}
	
	@Override
	public BaseDTO getEntity(RequestDTO requestDTO) {
	      ValidationUtility.validateRequestObject(requestDTO);
		return this.getEntityFromDatabase(requestDTO);
	}

	@Override
	public BaseDTO createEntity(BaseDTO baseDTO) {
		ValidationUtility.validateRequiredFieldsInDTO(baseDTO);
		LBLogger.logMessage("Create Entity - Valid DTO object");
		return this.createEntityInDatabase(baseDTO);
	}
	
	@Override
	public List<BaseDTO> getAllEntity(RequestDTO requestDTO) {
		return this.getAllEntityFromDatabase(requestDTO);
	}
	
	
	@Override
	public BaseDTO updateEntity(BaseDTO baseDTO) {
		LBLogger.logMessage("updateEntity : -","Database Service");
		return this.updateEntityInDatabase(baseDTO);
	}
	
	
	private BaseDTO updateEntityInDatabase(BaseDTO baseDTO) {
		
		BaseDTO resultDTO = null;
		try {
			this.database.openConnection();
			resultDTO = this.database.updateEntity(baseDTO);
		}catch(Exception ex){
			throw ex;
		}finally {
			this.database.closeConnection();
		}
		return resultDTO;
	}
	
	private BaseDTO createEntityInDatabase(BaseDTO baseDTO) {
		
	    BaseDTO resultDTO = null;
		try {
			this.database.openConnection();
			resultDTO = this.database.createEntity(baseDTO);
		}catch(Exception ex){
			if (ex instanceof RuntimeException) {
				throw ex;
			}
		}finally {
			this.database.closeConnection();
		}
		return resultDTO;
	}
	
	
	private BaseDTO getEntityFromDatabase(RequestDTO requestDTO) {
		
		BaseDTO resultDTO = null;
		
		try {
			this.database.openConnection();
			resultDTO = this.database.getEntity(requestDTO);
		}catch(Exception ex) {
			if (ex instanceof RuntimeException) {
				throw ex;
			}
		}finally {
			this.database.closeConnection();
		}
		
		return resultDTO;
	}
	
	private List<BaseDTO> getAllEntityFromDatabase(RequestDTO requestDTO){
		List<BaseDTO> resultList = null;
		try {
			this.database.openConnection();
			resultList = this.database.getAllEntity(requestDTO);
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}finally {
			this.database.closeConnection();
		}
		return resultList;
	}

	

}
