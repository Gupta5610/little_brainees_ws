package com.little_brainees.app.ws.utilities;

import com.little_brainees.app.ws.DTO.*;
import com.little_brainees.app.ws.exceptions.ErrorMessages;
import com.little_brainees.app.ws.exceptions.MissingRequiredFieldException;

public final class ValidationUtility {
	 
	
	private static MissingRequiredFieldException exception = new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
	
	
	public static void validateRequiredFieldsInDTO(BaseDTO baseDTO) throws MissingRequiredFieldException {
		
		 if(baseDTO instanceof TeacherDTO) {
			 validateRequiredFeildsInTeacherDTO((TeacherDTO)baseDTO);
		 }else if (baseDTO instanceof ClassDTO) {
			 validateRequiredFeildsInClassDTO((ClassDTO)baseDTO);
		 }else if (baseDTO instanceof SubjectDTO) {
			 validateRequiredFeildsInSubjectDTO((SubjectDTO)baseDTO);
		 }
	}
	
	
	
	
	private  static void validateRequiredFeildsInTeacherDTO(TeacherDTO teacherDTO) throws MissingRequiredFieldException {
		
		if (teacherDTO.getTeacherAddress() == null || teacherDTO.getTeacherAddress().isEmpty() ||
				teacherDTO.getTeacherAdharNumber() == null || teacherDTO.getTeacherAddress().isEmpty() ||
				teacherDTO.getTeacherBio() == null || teacherDTO.getTeacherBio().isEmpty() ||
				teacherDTO.getTeacherClass() == null || teacherDTO.getTeacherClass().isEmpty() ||
				teacherDTO.getTeacherEmail() == null || teacherDTO.getTeacherEmail().isEmpty() ||
				teacherDTO.getTeacherID() == null || teacherDTO.getTeacherClass().isEmpty() ||
				teacherDTO.getTeacherName() == null || teacherDTO.getTeacherName().isEmpty() ||
				teacherDTO.getTeacherPhoneNumber() == null || teacherDTO.getTeacherPhoneNumber().isEmpty() ) {
			throw exception;
		}
	}
	
	private  static void validateRequiredFeildsInClassDTO(ClassDTO classDTO) throws MissingRequiredFieldException {
		
		if (classDTO.getClassCode() == null || classDTO.getClassCode().isEmpty() ||
			classDTO.getClassName() == null || classDTO.getClassName().isEmpty()) {
			throw exception;
		}	
	}
	
	private static void validateRequiredFeildsInSubjectDTO(SubjectDTO subjectDTO) throws MissingRequiredFieldException {
		
		if (subjectDTO.getClassCode() == null || subjectDTO.getClassCode().isEmpty() ||
				subjectDTO.getSubjectName() == null || subjectDTO.getSubjectName().isEmpty() ||
				subjectDTO.getSubjectCode() == null || subjectDTO.getSubjectCode().isEmpty()) {
			throw exception;
		} 
	}
	
	
}
