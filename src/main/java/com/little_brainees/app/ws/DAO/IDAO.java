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
public interface IDAO {
	
	public BaseDTO createEntity(BaseDTO requestDTO);
	public List<BaseDTO> getAllEntity(RequestDTO requestDTO);
	public BaseDTO getEntity(RequestDTO requestDTO);
	public BaseDTO updateEntity(BaseDTO baseDTO);
	public void openConnection();
	public void closeConnection();
	
     
}
