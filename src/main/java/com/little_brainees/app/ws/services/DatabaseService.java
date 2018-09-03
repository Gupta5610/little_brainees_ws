/**
 * 
 */
package com.little_brainees.app.ws.services;

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
    
	private IMySQLDAO database = new MySQLDAO();
	
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
	
	
	private BaseDTO createEntityInDatabase(BaseDTO baseDTO) {
		
	    BaseDTO resultDTO = null;
		try {
			this.database.openConnection();
			resultDTO = this.database.saveEntity(baseDTO);
			LBLogger.logMessage("ResultDTO is Null : "+(resultDTO == null));
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

}
