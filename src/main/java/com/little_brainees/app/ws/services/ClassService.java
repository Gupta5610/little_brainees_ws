/**
 * 
 */
package com.little_brainees.app.ws.services;

import java.util.Collection;

import com.little_brainees.app.ws.DTO.ClassDTO;
import com.little_brainees.app.ws.exceptions.MissingRequiredFieldException;

/**
 * @author ashish
 *
 */
public interface ClassService {
	
	ClassDTO createClass(ClassDTO classDTO) throws MissingRequiredFieldException;
	ClassDTO getClassby(String id) throws Exception;
	Collection<ClassDTO> getAllClass();
}
