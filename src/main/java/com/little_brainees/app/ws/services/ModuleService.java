/**
 * 
 */
package com.little_brainees.app.ws.services;

import java.util.List;

import com.little_brainees.app.ws.DTO.ModuleDTO;

/**
 * @author ashish
 *
 */
public interface ModuleService {

	public ModuleDTO createModule(ModuleDTO moduleDTO);
	public List<ModuleDTO> getModules();
	public ModuleDTO getModuletByModuleCode(String moduleCode);
}
