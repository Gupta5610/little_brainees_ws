/**
 * 
 */
package com.little_brainees.app.ws.services;

import java.util.List;

import com.little_brainees.app.ws.DTO.BaseDTO;
import com.little_brainees.app.ws.shared.RequestDTO;

/**
 * @author ashish
 *
 */
public interface IDatabaseService {
	
	public BaseDTO getEntity(RequestDTO requestDTO);
	public BaseDTO createEntity(BaseDTO baseDTO);
	public List<BaseDTO> getAllEntity(RequestDTO requestDTO);
	
}
