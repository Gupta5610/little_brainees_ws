package com.little_brainees.app.ws.DAO;

import com.little_brainees.app.ws.DTO.*;
import com.little_brainees.app.ws.shared.RequestDTO;

public interface IMySQLDAO {
	
	public BaseDTO saveEntity(BaseDTO requestDTO);
	public BaseDTO getEntity(RequestDTO requestDTO);
	// =============================================================================================
	public void openConnection();
	public void closeConnection();
	// =============================================================================================
    

}
