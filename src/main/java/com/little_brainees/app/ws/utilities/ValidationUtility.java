package com.little_brainees.app.ws.utilities;

import com.little_brainees.app.ws.DTO.*;
import com.little_brainees.app.ws.exceptions.ErrorMessages;
import com.little_brainees.app.ws.exceptions.MissingRequiredFieldException;
import com.little_brainees.app.ws.shared.RequestDTO;

public final class ValidationUtility {
	 
	
	private static MissingRequiredFieldException missingRequiredFieldException = new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

	
	public static void validateRequestObject(RequestDTO requestDTO) {
		if(requestDTO.getRequestString() == null || requestDTO.getRequestString().isEmpty() ||
		   requestDTO.getType() == null ) {
			throw missingRequiredFieldException;
		}
		
	}
	
	public static void validateRequiredFieldsInDTO(BaseDTO baseDTO) {
		
		 if(baseDTO instanceof TeacherDTO) {
			 validateRequiredFeildsInTeacherDTO((TeacherDTO)baseDTO);
		 }else if (baseDTO instanceof ClassDTO) {
			 validateRequiredFeildsInClassDTO((ClassDTO)baseDTO);
		 }else if (baseDTO instanceof SubjectDTO) {
			 validateRequiredFeildsInSubjectDTO((SubjectDTO)baseDTO);
		 }else if (baseDTO instanceof TopicDTO) {
			 validateRequiredFeildsInTopicDTO((TopicDTO)baseDTO);
		 }
	}
	
	private  static void validateRequiredFeildsInTeacherDTO(TeacherDTO teacherDTO)  {
		
		LBLogger.logMessage("validateRequiredFeildsInTeacherDTO - ", teacherDTO.toString());
		
		if (teacherDTO.getTeacherAddress() == null || teacherDTO.getTeacherAddress().isEmpty() ||
				teacherDTO.getTeacherAdharNumber() == null || teacherDTO.getTeacherAddress().isEmpty() ||
				teacherDTO.getTeacherBio() == null || teacherDTO.getTeacherBio().isEmpty() ||
				teacherDTO.getTeacherClass() == null || teacherDTO.getTeacherClass().isEmpty() ||
				teacherDTO.getTeacherEmail() == null || teacherDTO.getTeacherEmail().isEmpty() ||
				teacherDTO.getTeacherID() == null || teacherDTO.getTeacherClass().isEmpty() ||
				teacherDTO.getTeacherName() == null || teacherDTO.getTeacherName().isEmpty() ||
				teacherDTO.getTeacherPhoneNumber() == null || teacherDTO.getTeacherPhoneNumber().isEmpty() ) {
			throw missingRequiredFieldException;
		}
	}
	
	private  static void validateRequiredFeildsInClassDTO(ClassDTO classDTO) throws MissingRequiredFieldException {
		LBLogger.logMessage("validateRequiredFeildsInClassDTO - ", classDTO.toString());
		if (classDTO.getClassCode() == null || classDTO.getClassCode().isEmpty() ||
			classDTO.getClassName() == null || classDTO.getClassName().isEmpty()) {
			throw missingRequiredFieldException;
		}	
	}
	
	private static void validateRequiredFeildsInSubjectDTO(SubjectDTO subjectDTO) throws MissingRequiredFieldException {
		
		LBLogger.logMessage("validateRequiredFeildsInSubjectDTO - ", subjectDTO.toString());
		
		if (subjectDTO.getClassCode() == null || subjectDTO.getClassCode().isEmpty() ||
				subjectDTO.getSubjectName() == null || subjectDTO.getSubjectName().isEmpty() ||
				subjectDTO.getSubjectCode() == null || subjectDTO.getSubjectCode().isEmpty()) {
			throw missingRequiredFieldException;
		} 
	}
	
	private static void validateRequiredFeildsInModuleDTO(ModuleDTO moduleDTO) throws MissingRequiredFieldException {
		
		LBLogger.logMessage("validateRequiredFeildsInSubjectDTO - ", moduleDTO.toString());
		
		if (moduleDTO.getModuleCode() == null || moduleDTO.getModuleCode().isEmpty() ||
				moduleDTO.getModuleName() == null || moduleDTO.getModuleName().isEmpty() ||
				moduleDTO.getSubjectCode() == null || moduleDTO.getSubjectCode().isEmpty()) {
			throw missingRequiredFieldException;
		}
	}
	
	private static void validateRequiredFeildsInTopicDTO(TopicDTO topicDTO) throws MissingRequiredFieldException{
		
		LBLogger.logMessage("validateRequiredFeildsInTopicDTO - ", topicDTO.toString());
		
		if(topicDTO.getModuleCode() == null || topicDTO.getModuleCode().isEmpty() ||
		   topicDTO.getTopicCode() == null || topicDTO.getTopicCode().isEmpty() ||
		   topicDTO.getTopicName() == null || topicDTO.getTopicName().isEmpty()) {
			LBLogger.logMessage("Object Not valid");
			throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
	}
	
}
