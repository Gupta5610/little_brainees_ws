/**
 * 
 */
package com.little_brainees.app.ws.services;

import java.util.List;

import com.little_brainees.app.ws.DAO.*;
import com.little_brainees.app.ws.DTO.ModuleDTO;
import com.little_brainees.app.ws.utilities.ValidationUtility;

/**
 * @author ashish
 *
 */
public class ModuleServiceImp implements ModuleService{

	
	public static ModuleService shared = new ModuleServiceImp();
	
	private DAO database;
	
	public ModuleServiceImp() {
		this.database = new BaseMySQLDAO();
	}
	
	
	@Override
	public ModuleDTO createModule(ModuleDTO moduleDTO) {
		ValidationUtility.validateRequiredFieldsInDTO(moduleDTO);
		return this.saveModule(moduleDTO);
	}
	
	
	private ModuleDTO saveModule(ModuleDTO moduleDTO) {
		ModuleDTO savedDTO = null;
		try {
			this.database.openConnection();
			savedDTO = this.database.saveModule(moduleDTO);
		}finally {
			this.database.closeConnection();
		}
		return savedDTO;
	}

	@Override
	public List<ModuleDTO> getModules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleDTO getModuletByModuleCode(String moduleCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
