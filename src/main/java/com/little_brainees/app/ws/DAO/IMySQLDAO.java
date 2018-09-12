package com.little_brainees.app.ws.DAO;

import java.util.List;

import com.little_brainees.app.ws.DTO.*;
import com.little_brainees.app.ws.shared.RequestDTO;

public interface IMySQLDAO {
	
	public BaseDTO saveEntity(BaseDTO requestDTO);
	public BaseDTO getEntity(RequestDTO requestDTO);
	public List<BaseDTO>getAllEntity(RequestDTO requestDTO);
	public BaseDTO updateEntity(BaseDTO requestDTO);
	// =============================================================================================
	public void openConnection();
	public void closeConnection();
	// =============================================================================================
    

}
