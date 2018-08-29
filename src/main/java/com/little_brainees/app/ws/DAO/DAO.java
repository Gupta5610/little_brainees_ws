package com.little_brainees.app.ws.DAO;

import com.little_brainees.app.ws.DTO.*;

public interface DAO {
	
	// =============================================================================================
	public void openConnection();
	public void closeConnection();
	// =============================================================================================
	public TeacherDTO getTeacherByEmail(String email);
	public TeacherDTO saveTeacher(TeacherDTO teacherDTO);
	// =============================================================================================
	public ClassDTO getClassByClassCode(String classCode);
	public ClassDTO saveClass(ClassDTO classDTO);
	// =============================================================================================
	public SubjectDTO getSubjectBySubjectCode(String subjectCode);
	public SubjectDTO saveSubject(SubjectDTO subjectDTO);

}
