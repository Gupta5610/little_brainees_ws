/**
 * 
 */
package com.little_brainees.app.ws.DAO;

import java.util.List;

import com.little_brainees.app.ws.DTO.BaseDTO;
import com.little_brainees.app.ws.shared.RequestDTO;

/**
 * @author ashish
 *
 */
public class DAO implements IDAO {

	
	private MySQLDAO mySqlDatabase = new MySQLDAO();
	
	
	@Override
	public BaseDTO createEntity(BaseDTO requestDTO) {
		return this.mySqlDatabase.saveEntity(requestDTO);
	}

	
	public List<BaseDTO> getAllEntity(RequestDTO requestDTO) {
		return this.mySqlDatabase.getAllEntity(requestDTO);
	}

	@Override
	public BaseDTO getEntity(RequestDTO requestDTO) {
		
		return this.mySqlDatabase.getEntity(requestDTO);
	}

	@Override
	public BaseDTO updateEntity(BaseDTO baseDTO) {
		
		return this.mySqlDatabase.updateEntity(baseDTO);
	}

	@Override
	public void openConnection() {
		this.mySqlDatabase.openConnection();
	}

	@Override
	public void closeConnection() {
		this.mySqlDatabase.closeConnection();
	}
	
}
