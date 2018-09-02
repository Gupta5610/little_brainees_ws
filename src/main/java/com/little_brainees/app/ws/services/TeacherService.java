/**
 * 
 */
package com.little_brainees.app.ws.services;
import com.little_brainees.app.ws.DTO.*;
import com.little_brainees.app.ws.exceptions.MissingRequiredFieldException;

/**
 * @author ashish
 *
 */
public interface TeacherService {

	TeacherDTO CreateTeacher(TeacherDTO teacherDTO);
	TeacherDTO getTeacherByEmail(String email);
	TeacherDTO randomteacher();
}
