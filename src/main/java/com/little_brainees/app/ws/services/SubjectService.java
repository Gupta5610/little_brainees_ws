/**
 * 
 */
package com.little_brainees.app.ws.services;

import java.util.List;

import com.little_brainees.app.ws.DTO.SubjectDTO;

/**
 * @author ashish
 *
 */
public interface SubjectService {

	public SubjectDTO createSubject(SubjectDTO subjectDTO);
	public List<SubjectDTO> getSubjects();
	public SubjectDTO getSubjectBy(String subjectCode);
	
}
